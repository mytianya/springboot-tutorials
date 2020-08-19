package vip.codehome.springboot.tutorials.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component

@Slf4j
public class ScheduledTask {
    @Autowired
    ScheduledTaskRegistrar scheduledTaskRegistrar;
    @Scheduled(cron = "*/1 * * * * ?")
    public void cronTask1(){
        try {
            Thread.sleep(5100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("CronTask-当方法的执行时间超过任务调度频率时，调度器会在下个周期执行");
    }
    @Scheduled(fixedRate = 1000)
    public void cronTask2(){
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("fixedRate--固定频率执行，当前执行任务如果超时，调度器会在当前方法执行完成后立即执行");
    }
    @Scheduled(fixedDelay = 1000)
    public void cronTask3(){
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("fixedDelay---固定间隔执行，从上一次执行任务的结束时间开始算-------");
//        while (true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
