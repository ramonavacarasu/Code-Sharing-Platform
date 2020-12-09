package platform.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.repository.ICodeRepository;
import platform.view.ViewComponent;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class CodeController {

    private final ICodeRepository repository;
    private final ViewComponent viewComponent;

    ArrayList<Code> snippets = new ArrayList<>();

    private Code code = new Code();

    public CodeController(ICodeRepository repository,
                          ViewComponent viewComponent) {
        this.repository = repository;
        this.viewComponent = viewComponent;
    }

    @GetMapping(value = "/code")
    String getCode() {
        if (code.getCode() == null) {
            code.setCode(repository.getCode());
            code.setDate(repository.getDate());
        }
        return viewComponent.getAllCode("code", code);
    }

    @GetMapping(value = "/api/code")
    Code getApiCode() {
        if (code.getCode() == null) {
            code.setCode(repository.getCode());
            code.setDate(repository.getDate());
        }
        return new Code(code.getCode(), code.getDate(), code.getId());
    }

   /* @PostMapping(value = "/api/code/new")
    ResponseEntity postCode(@RequestBody Code code1) {
        code.setCode(code1.getCode());
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }*/


    // Stage 3
    @PostMapping(value = "/api/code/new", consumes = MediaType.APPLICATION_JSON_VALUE,
                                          produces = "application/json;charset=UTF-8")
    String postApiCodeNew(@RequestBody Code newCode) {
        Code c = new Code();
        c.setCode(newCode.getCode());
        c.setDate(repository.getDate());
        c.setId(snippets.size() + 1 + "");
        snippets.add(c);
        return "{ \"id\" : \"" + c.getId() + "\" }";
    }

    @GetMapping(value = "/api/code/{id}")
    Code getApiCodeN(@PathVariable(value = "id") String id,
                     HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        int index = Integer.parseInt(id) - 1;
        return snippets.get(index);
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json;charset=UTF-8")
    ArrayList<Code> getApiCodeLatest() {
        ArrayList<Code> latest = new ArrayList<>();
        int index = snippets.size() - 1;

        while (latest.size() < 10 && index >= 0) {
            latest.add(snippets.get(index));
            index--;
        }
        return latest;
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    String getCodeNew() {
        return viewComponent.getAllCode("codeNew", code);
    }

    @GetMapping(value = "/code/{id}")
    String getCodeN(@PathVariable(value = "id") String id, HttpServletResponse response) {
        response.addHeader("Content-Type", "text/html");
        code = snippets.get(Integer.parseInt(id) - 1);
        return viewComponent.getAllCode("code", code);
    }

    @GetMapping(value = "/code/latest", produces = "text/html")
        String getCodeLatest() {
        ArrayList<Code> latest = new ArrayList<>();
        int index = snippets.size() - 1;

        while (latest.size() < 10 && index >= 0) {
            latest.add(snippets.get(index));
            index--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < latest.size(); i++) {
            sb.append(viewComponent.getAllCode("latest", latest.get(i)));
        }
        return sb.toString();
    }

}
