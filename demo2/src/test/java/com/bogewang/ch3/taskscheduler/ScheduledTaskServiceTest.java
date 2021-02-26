package com.bogewang.ch3.taskscheduler;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by bogewang on 2017/7/10.
 */
public class ScheduledTaskServiceTest {
    @Test
    public void reportCurrentTime() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);
    }

}