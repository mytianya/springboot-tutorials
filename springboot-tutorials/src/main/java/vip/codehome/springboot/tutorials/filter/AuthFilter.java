package vip.codehome.springboot.tutorials.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/***
 *@author zyw
 *@createTime 2020/8/17 10:37
 *@description
 *@version 1.0
 * WebFilter这个注解并没有指定执行顺序的属性，其执行顺序依赖于Filter的名称
 * 是根据Filter类名（注意不是配置的filter的名字）的字母顺序倒序排列
 * ，并且@WebFilter指定的过滤器优先级都高于FilterRegistrationBean配置的过滤器。
 */
@WebFilter(urlPatterns = "/*",filterName = "authFiler")
@Slf4j
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("进行权限校验.........");
        chain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
