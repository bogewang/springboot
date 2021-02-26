package com.bogewang.ch3.taskscheduler;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by bogewang on 2017/7/10.
 */
@Configuration
@ComponentScan("com.bogewang.ch3.taskscheduler")
@EnableScheduling       //1 开启对计划任务的支持;
public class TaskSchedulerConfig {
}
