package com.bogewang.ch3.taskscheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bogewang on 2017/7/10.
 */
@Service
public class ScheduledTaskService {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)    //1 使用fixedRate属性每隔固定时间执行
    public void reportCurrentTime(){
        System.out.println("每隔五秒执行一次: " + dateFormat.format(new Date()));
    }

    @Scheduled(cron = "0 32 13 ? * *")      //2 按照指定时间执行,本例指的是每天 11.28 分执行; cron只unix系统下的定时任务;
    public void fixTimeExecution(){
        System.out.println("在指定时间 " + dateFormat.format(new Date()) + "执行");
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(); //single threaded by default
    }
}
