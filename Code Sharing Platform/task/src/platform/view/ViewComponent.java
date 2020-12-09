package platform.view;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class ViewComponent {

    private final ResourceLoader resourceLoader;

    public ViewComponent(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public <T> String getAllCode(String fileName, T model) {

        Resource resource = resourceLoader.
                getResource("classpath:/views/" + fileName + ".html");

        String code;
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            code = FileCopyUtils.copyToString(reader);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        Pattern pattern = Pattern.compile("\\{\\{\\w+}}");

        code = pattern.matcher(code).replaceAll(matchResult -> {

            String bind = matchResult.group().replace("{", "")
                    .replace("}", "");

            String methodName = "get" + bind.substring(0,1).toUpperCase()
                    + bind.substring(1);
            // System.out.println(methodName);

            Class<?> t = model.getClass();

            try {
                Method method = t.getMethod(methodName);
                return method.invoke(model).toString();
            } catch (Exception e) {
                return "";
            }
        });
        System.out.println(code);
        return code;
    }

}
