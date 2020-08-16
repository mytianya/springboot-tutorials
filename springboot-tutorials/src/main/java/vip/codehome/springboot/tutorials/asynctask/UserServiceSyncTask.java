package vip.codehome.springboot.tutorials.asynctask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class UserServiceSyncTask {
    @Async
    public void sendEmail(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Thread.currentThread().getName());
    }
    @Async
    public Future<String> echo(String msg){
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>(Thread.currentThread().getName()+"hello world !!!!");
        } catch (InterruptedException e) {
            //
        }
        return null;
    }
}
