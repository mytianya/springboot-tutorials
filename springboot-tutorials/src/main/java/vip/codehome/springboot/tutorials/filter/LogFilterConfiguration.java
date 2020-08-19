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
        //匹配的过滤器
        registrationBean.addUrlPatterns("/*");
        //过滤器名称
        registrationBean.setName("logFilter");
        //过滤器顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
