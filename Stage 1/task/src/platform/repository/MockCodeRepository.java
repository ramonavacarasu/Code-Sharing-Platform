package platform.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MockCodeRepository implements ICodeRepository {

    @Override
    public String getCode() {
        return "public static void main(String[] args) {\n" +
                "    SpringApplication.run(CodeSharingPlatform.class, args);\n" +
                "}";
    }
}
