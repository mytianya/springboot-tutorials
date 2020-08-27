package vip.codehome.springboot.tutorials;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("vip.codehome")
@EnableSwagger2
@EnableAsync
@ServletComponentScan("vip.codehome.springboot.tutorials.filter")
public class SpringbootTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTutorialsApplication.class, args);
    }

}
