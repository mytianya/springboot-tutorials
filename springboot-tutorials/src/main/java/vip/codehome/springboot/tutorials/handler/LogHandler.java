package vip.codehome.springboot.tutorials.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 *@author zyw
 *@createTime 2020/8/17 11:26
 *@description 单例
 *@version 1.0
 */
@Slf4j
public class LogHandler extends HandlerInterceptorAdapter {
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<>("StopWatch-StartTime");

    public LogHandler() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间      
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;//继续流程
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）  
        long consumeTime = endTime - beginTime;
        //3、消耗的时间         
        log.info(String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
