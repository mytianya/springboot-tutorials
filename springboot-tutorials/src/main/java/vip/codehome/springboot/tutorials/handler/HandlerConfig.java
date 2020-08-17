package vip.codehome.springboot.tutorials.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 *@author zyw
 *@createTime 2020/8/17 11:32
 *@description
 *@version 1.0
 * 1. Filter是servlet规范，使用范围是web程序，拦截器不限于web程序，也可以用于Application、Swing程序中
 * 2. Filter是servlet规范中定义，是servlet容器支持的。拦截器是Spring容器内，是spring框架支持的
 * 3. 拦截器是Spring的一个组件，额能够使用spring中对象，如Service对象、数据源、事务管理、通过IOC注入容器即可，filter则不能
 * 4. filter在servlet前后起作用，拦截器能够深入方法的前后，异常抛出前后。
 * 5. 所以在springboot项目中一般优先使用拦截器
 */
@Configuration
public class HandlerConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogHandler());
    }
}
