package platform.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import platform.entity.Code;
import platform.repository.ICodeRepository;
import platform.view.ViewComponent;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {

    private final ICodeRepository repository;
    private final ViewComponent viewComponent;

    public CodeService(ICodeRepository repository, ViewComponent viewComponent) {
        this.repository = repository;
        this.viewComponent = viewComponent;
    }

    public String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
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
        return LocalDateTime.now().format(formatter);
    }

    public List<Code> getLatest() {
        PageRequest pageable = PageRequest.of(0, 10);
        return repository.findLatest(false, false,
                pageable).getContent();
    }

    public long consumedTime(String date) {
        String[] dateTime = date.split(" ");
        LocalTime dateOfCode = LocalTime.parse(dateTime[1]);
        LocalTime thisMoment = LocalTime.now();
        long seconds = thisMoment.toSecondOfDay() - dateOfCode.toSecondOfDay();
        System.out.println(thisMoment.toSecondOfDay() + ":" + dateOfCode.toSecondOfDay());
        return seconds;
    }

    public long countItems() {
        return repository.count();
    }
}
