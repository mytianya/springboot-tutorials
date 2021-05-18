package vip.codehome.springboot.tutorials.healthIndicator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/***
 * @author 道士吟诗
 * @date 2021/5/7-下午10:46
 * @description
 * @Endpoint：定义一个监控端点，同时支持 HTTP 和 JMX 两种方式。
 * @WebEndpoint：定义一个监控端点，只支持 HTTP 方式。
 * @JmxEndpoint：定义一个监控端点，只支持 JMX 方式
 * @ReadOperation：作用在方法上，可用来返回端点展示的信息（通过 Get 方法请求）。
 * @WriteOperation：作用在方法上，可用来修改端点展示的信息（通过 Post 方法请求）。
 * @DeleteOperation：作用在方法上，可用来删除对应端点信息（通过 Delete 方法请求）。
 * @Selector：作用在参数上，用来定位一个端点的具体指标路由。
 ***/
@Endpoint(id = "codehome")
public class MyEndpoint {
    String blogUrl="www.codehome.vip";
    String author="dsys";
    @ReadOperation
    public String blog(){
        return blogUrl;
    }
    @ReadOperation
    public String author(){
        return author;
    }
    @ReadOperation
    public String test(@Selector String name){
        if("blog".equals(name)){
            return blogUrl;
        }
        if("author".equals(name)){
            return author;
        }
        return null;
    }
    @WriteOperation
    public void setConfigs(@Selector String name,String value){
        if("blog".equals(name)){
             this.blogUrl=value;
        }
        if("author".equals(name)){
            this.author=name;
        }
    }
}
