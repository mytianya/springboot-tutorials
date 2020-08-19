package vip.codehome.springboot.tutorials.scheduled;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
@EnableScheduling
@Configuration
public class ScheduledTaskConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }

    public ThreadPoolTaskScheduler  taskExecutor() {
        ThreadPoolTaskScheduler scheduler=new ThreadPoolTaskScheduler();
        // 设置核心线程数
        scheduler.setPoolSize(8);
        // 设置默认线程名称
        scheduler.setThreadNamePrefix("CodehomeScheduledTask-");
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.initialize();
        return scheduler;
    }
}
