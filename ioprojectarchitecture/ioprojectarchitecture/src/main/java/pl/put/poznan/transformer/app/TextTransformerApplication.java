package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.put.poznan.transformer.logic.MainStory;

import java.io.IOException;

/**
 *
 */
@EnableAutoConfiguration
@Controller
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    /**
     * @return
     * @throws IOException
     */
    @RequestMapping("/")
    @ResponseBody
    public String mainPage() throws IOException {
        MainStory sc = new MainStory();
        String path = ("ioprojectarchitecture/ioprojectarchitecture/src/main/java/pl/put/poznan/transformer/app/scenario.txt");
        try {

            return sc.transformToPoints(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Kupa";
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
