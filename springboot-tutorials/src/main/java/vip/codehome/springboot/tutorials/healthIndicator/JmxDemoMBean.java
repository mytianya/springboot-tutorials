package vip.codehome.springboot.tutorials.healthIndicator;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/***
 * @author 道士吟诗
 * @date 2021/5/7-下午10:36
 * @description
 ***/
@Component
@ManagedResource(objectName = "vip.codehome:name=jxmdemo",description = "jmx test")
public class JmxDemoMBean {
    private long version=1;
    @ManagedAttribute
    public long getVersion(){
        return version;
    }
    @ManagedOperation
    public void change(int version){
        this.version=version;
    }
}
