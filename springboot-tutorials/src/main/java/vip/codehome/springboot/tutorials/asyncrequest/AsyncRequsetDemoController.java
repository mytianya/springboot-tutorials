package vip.codehome.springboot.tutorials.asyncrequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author zyw
 * @mail dsyslove@163.com
 * @createtime 2021/5/18--15:38
 * @description
 **/
@RestController
@Slf4j
public class AsyncRequsetDemoController {
  private ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("longPolling-timeout-checker-%d")
      .build();
  private ScheduledExecutorService timeoutChecker = new ScheduledThreadPoolExecutor(1, threadFactory);
  @GetMapping("/asynccontext")
  public void asyncContext(HttpServletRequest request, HttpServletResponse response){
    AsyncContext asyncContext=request.startAsync();
    asyncContext.addListener(new AsyncListener() {
      @Override
      public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("执行完成");
      }

      @Override
      public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("超时了");
      }

      @Override
      public void onError(AsyncEvent event) throws IOException {
        System.out.println("发生错误");
      }

      @Override
      public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("异步请求开始");
      }
    });
//    asyncContext.start(()->{
//      try {
//        //长时间耗时服务
//        asyncContext.getResponse().getWriter().write("hello async");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      asyncContext.complete();
//    });
    //不用start创建的线程
//    Runnable runnable=()->{
//      try {
//        //长时间耗时服务
//        asyncContext.getResponse().getWriter().write("hello async");
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//      asyncContext.complete();
//    };
//    new Thread(runnable).start();
    //使用线程池
    timeoutChecker.submit(()->{
      try {
        //长时间耗时服务
        asyncContext.getResponse().getWriter().write("hello async");
      } catch (IOException e) {
        e.printStackTrace();
      }
      asyncContext.complete();
    });
  }
  @RequestMapping(value = "/email/callableReq", method = GET)
  @ResponseBody
  public Callable<String> callableReq () {
    System.out.println("外部线程：" + Thread.currentThread().getName());

    return new Callable<String>() {

      @Override
      public String call() throws Exception {
        Thread.sleep(10000);
        System.out.println("内部线程：" + Thread.currentThread().getName());
        return "callable!";
      }
    };
  }

  @Configuration
  public class RequestAsyncPoolConfig extends WebMvcConfigurerAdapter {

    @Resource
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;

    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
      //处理 callable超时
      configurer.setDefaultTimeout(60*1000);
      configurer.setTaskExecutor(myThreadPoolTaskExecutor);
      configurer.registerCallableInterceptors(timeoutCallableProcessingInterceptor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutCallableProcessingInterceptor() {
      return new TimeoutCallableProcessingInterceptor();
    }
  }

  @RequestMapping(value = "/email/webAsyncReq", method = GET)
  @ResponseBody
  public WebAsyncTask<String> webAsyncReq () {
    System.out.println("外部线程：" + Thread.currentThread().getName());
    Callable<String> result = () -> {
      System.out.println("内部线程开始：" + Thread.currentThread().getName());
      try {
        TimeUnit.SECONDS.sleep(4);
      } catch (Exception e) {
        // TODO: handle exception
      }
      log.info("副线程返回");
      System.out.println("内部线程返回：" + Thread.currentThread().getName());
      return "success";
    };
    WebAsyncTask<String> wat = new WebAsyncTask<String>(3000L, result);
    wat.onTimeout(new Callable<String>() {
      @Override
      public String call() throws Exception {
        // TODO Auto-generated method stub
        return "超时";
      }
    });
    return wat;
  }
  @RequestMapping(value = "/email/deferredResultReq", method = GET)
  @ResponseBody
  public DeferredResult<String> deferredResultReq () {
    System.out.println("外部线程：" + Thread.currentThread().getName());
    //设置超时时间
    DeferredResult<String> result = new DeferredResult<String>(60*1000L);
    //处理超时事件 采用委托机制
    result.onTimeout(new Runnable() {

      @Override
      public void run() {
        System.out.println("DeferredResult超时");
        result.setResult("超时了!");
      }
    });
    result.onCompletion(new Runnable() {

      @Override
      public void run() {
        //完成后
        System.out.println("调用完成");
      }
    });
    timeoutChecker.execute(new Runnable() {
      @Override
      public void run() {
        //处理业务逻辑
        System.out.println("内部线程：" + Thread.currentThread().getName());
        //返回结果
        result.setResult("DeferredResult!!");
      }
    });
    return result;
  }
}
