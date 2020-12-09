package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.service.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class CodeController {

    private final CodeService service;

    public CodeController(CodeService service) {
        this.service = service;
    }

    @PostMapping(value = "/api/code/new",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json;charset=UTF-8")
    String postApiCodeNew(@RequestBody Code newCode) {
        Code code = new Code();
        code.setId(service.getIndex() + 1 + "");
        code.setCode(newCode.getCode());
        code.setDate(service.getDate());
        service.saveCode(code);
        return "{ \"id\" : \"" + code.getId() + "\" }";
    }

    @GetMapping(value = "/api/code/{id}")
    Code getApiCodeN(@PathVariable(value = "id") String id,
                     HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return service.getCode(id);
    }

    @GetMapping(value = "/api/code/latest",
            produces = "application/json;charset=UTF-8")
    ArrayList<Code> getApiCodeLatest() {
        ArrayList<Code> latest = new ArrayList<>();
        long index = service.getIndex();
        while (latest.size() < 10 && index >= 0) {
            Code lastCode = service.getCode(index + "");
            if (lastCode != null) {
                latest.add(lastCode);
            }
            index--;
        }
        return latest;
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    String getCodeNew() {
        return service.getView("codeNew", new Code());
    }

    @GetMapping(value = "/code/{id}")
    String getCodeN(@PathVariable(value = "id") String id,
                    HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        return service.getView("code", service.getCode(id));
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
        String getCodeLatest() {
        ArrayList<Code> latest = new ArrayList<>();
        long index = service.getIndex();

        while (latest.size() < 10 && index >= 0) {
            Code lastCode = service.getCode(index + "");
            if (lastCode != null) {
                latest.add(lastCode);
            }
            index--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < latest.size(); i++) {
            sb.append(service.getView("latest", latest.get(i)));
        }
        return sb.toString();
    }

}
