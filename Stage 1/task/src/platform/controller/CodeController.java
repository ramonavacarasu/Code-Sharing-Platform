package platform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.entity.Code;
import platform.repository.ICodeRepository;
import platform.repository.MockCodeRepository;
import platform.view.ViewComponent;

@RestController
@RequestMapping
public class CodeController {

    private final ICodeRepository repository;
    private final ViewComponent viewComponent;

    public CodeController(ICodeRepository repository,
                          ViewComponent viewComponent) {
        this.repository = repository;
        this.viewComponent = viewComponent;
    }

    @GetMapping(value = "/code")
    String getCode() {
        Code code = new Code();
        code.setCode(repository.getCode());
        return viewComponent.getAllCode("code", code);
    }

    @GetMapping(value = "/api/code")
    Code getApiCode() {
        return new Code(repository.getCode());
    }

}
