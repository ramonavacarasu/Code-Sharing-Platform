package platform.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.repository.ICodeRepository;
import platform.view.ViewComponent;

@RestController
@RequestMapping
public class CodeController {

    private final ICodeRepository repository;
    private final ViewComponent viewComponent;

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
        return new Code(code.getCode(), code.getDate());
    }

    @PostMapping(value = "/api/code/new")
    ResponseEntity postCode(@RequestBody Code code1) {
        code.setCode(code1.getCode());
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/code/new")
    String getCodeNew() {
        return viewComponent.getAllCode("codeNew", code);
    }
}
