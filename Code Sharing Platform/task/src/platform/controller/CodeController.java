package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.entity.Code;
import platform.service.CodeService;
import java.util.List;

@RestController
public class CodeController {

    private CodeService service;

    @Autowired
    public CodeController(CodeService service) {
        this.service = service;
    }

    @PostMapping(value = "/api/code/new", produces = "application/json;charset=UTF-8",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    String postApiCodeNew(@RequestBody Code newCode) {
        Code code = new Code();
        code.setOrderTicket(service.countItems());
        code.setId(service.generateUUID());
        code.setCode(newCode.getCode());
        code.setTime(newCode.getTime());
        code.setViews(newCode.getViews());
        code.setDate(service.getDate());
        code.setTimeRestriction(newCode.getTime() > 0);
        code.setViewsRestriction(newCode.getViews() > 0);
        service.saveCode(code);
        return "{ \"id\" : \"" + code.getId() + "\" }";
    }

    @GetMapping(value = "/api/code/{id}", produces = "application/json;charset=UTF-8")
    Code getApiCodeN(@PathVariable(value = "id") String id) {
        Code code = service.getCode(id);
        if (code.isTimeRestriction() && code.isViewsRestriction()) {
            long consumedTime = service.consumedTime(code.getDate());
            if (consumedTime < code.getTime() && code.getViews() > 0) {
                code.setTime(code.getTime() - consumedTime);
                code.setViews(code.getViews() - 1);
                service.saveCode(code);
                return code;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else if (code.isTimeRestriction()) {
            long consumedTime = service.consumedTime(code.getDate());
            if (consumedTime < code.getTime()) {
                code.setTime(code.getTime() - consumedTime);
                service.saveCode(code);
                return code;
            }  else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } else if (code.isViewsRestriction()) {
            if (code.getViews() > 0) {
                code.setViews(code.getViews() - 1);
                service.saveCode(code);
                return code;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        return code;
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json;charset=UTF-8")
    List<Code> getApiCodeLatest() {
        return service.getLatest();
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    String getCodeNew() {
        return service.getView("codeNew", new Code());
    }

    @GetMapping(value = "/code/{id}", produces = "text/html")
    String getCodeN(@PathVariable(value = "id") String id) {
        Code code = service.getCode(id);
        if (code.isTimeRestriction() && code.isViewsRestriction()) {
            long consumedTime = service.consumedTime(code.getDate());
            if (consumedTime < code.getTime() && code.getViews() > 0) {
                code.setTime(code.getTime() - consumedTime);
                code.setViews(code.getViews() - 1);
                service.saveCode(code);
                return service.getView("codeWithRestriction", code);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else if (code.isTimeRestriction()) {
            long consumedTime = service.consumedTime(code.getDate());
            if (consumedTime < code.getTime()) {
                code.setTime(code.getTime() - consumedTime);
                service.saveCode(code);
                return service.getView("codeWithTimeRestriction", code);
            }  else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else if (code.isViewsRestriction()) {
            if (code.getViews() > 0) {
                code.setViews(code.getViews() - 1);
                service.saveCode(code);
                return service.getView("codeWithViewsRestriction", code);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }
        return service.getView("code", code);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
        String getCodeLatest() {
        List<Code> latest = service.getLatest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < latest.size(); i++) {
            sb.append(service.getView("latest", latest.get(i)));
        }
        return sb.toString();
    }

}
