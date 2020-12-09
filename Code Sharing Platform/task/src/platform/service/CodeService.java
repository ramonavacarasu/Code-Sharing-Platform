package platform.service;

import org.springframework.stereotype.Service;
import platform.entity.Code;
import platform.repository.ICodeRepository;
import platform.view.ViewComponent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CodeService {

    private final ICodeRepository repository;
    private final ViewComponent viewComponent;


    public CodeService(ICodeRepository repository, ViewComponent viewComponent) {
        this.repository = repository;
        this.viewComponent = viewComponent;
    }

    public void saveCode(Code code) {
        repository.save(code);
    }
    public String getView(String fileName, Code code) {
        return viewComponent.getAllCode(fileName, code);
    }
    public Code getCode(String id) {
        return repository.findById(id).orElse(null);
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        return date;
    }

    public long getIndex() {
        return repository.count();
    }
}
