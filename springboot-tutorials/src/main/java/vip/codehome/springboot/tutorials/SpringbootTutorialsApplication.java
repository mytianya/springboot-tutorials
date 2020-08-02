package vip.codehome.springboot.tutorials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("vip.codehome")
@EnableSwagger2
public class SpringbootTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTutorialsApplication.class, args);
    }

}
