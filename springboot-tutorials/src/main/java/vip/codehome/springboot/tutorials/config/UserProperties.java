package vip.codehome.springboot.tutorials.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Configuration
@ConfigurationProperties(prefix = "user")
@Data
public class UserProperties {
    String userName;
    int age;
    boolean forbidden;
}
