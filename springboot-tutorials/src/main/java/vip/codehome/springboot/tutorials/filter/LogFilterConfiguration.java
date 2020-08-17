package vip.codehome.springboot.tutorials.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 *@author zyw
 *@createTime 2020/8/17 10:33
 *@description
 *@version 1.0
 */
@Configuration
public class LogFilterConfiguration {
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("logFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
