package vip.codehome.springboot.tutorials;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@ComponentScan("vip.codehome")
@EnableSwagger2
@EnableAsync
@ServletComponentScan("vip.codehome.springboot.tutorials.filter")
//@MapperScan(basePackages = "vip.codehome.springboot.tutorials.mapper")
@EnableBatchProcessing
@EnableTransactionManagement
@EnableMBeanExport
public class SpringbootTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTutorialsApplication.class, args);
    }
}
