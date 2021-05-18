package vip.codehome.springboot.tutorials.asyncrequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

/***
 * @author 道士吟诗
 * @date 2021/5/18-下午11:08
 * @description
 ***/
@RestController
public class ConfigController {
    public Set<DeferredResult<String>> deferredResultSet=new HashSet<>();
    @GetMapping("/fetch")
    public DeferredResult<String> fetch(){
        DeferredResult<String> deferredResult=new DeferredResult<>();
        deferredResultSet.add(deferredResult);
        return deferredResult;
    }
    @PostMapping("/update")
    public void update(){
        for(DeferredResult deferredResult:deferredResultSet){
            deferredResult.setResult("ok");
        }
    }
}
