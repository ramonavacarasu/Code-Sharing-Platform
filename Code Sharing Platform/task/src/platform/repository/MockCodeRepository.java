package platform.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class MockCodeRepository implements ICodeRepository {

    @Override
    public String getCode() {
        return "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}";
    }

    @Override
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String date = LocalDateTime.now().format(formatter);
        return date;
    }
}
