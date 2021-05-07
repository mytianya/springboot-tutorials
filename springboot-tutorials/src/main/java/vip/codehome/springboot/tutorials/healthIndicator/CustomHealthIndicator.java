package vip.codehome.springboot.tutorials.healthIndicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/***
 * @author 道士吟诗
 * @date 2021/5/5-下午10:52
 * @description
 ***/
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("app","Alive");
    }
}
