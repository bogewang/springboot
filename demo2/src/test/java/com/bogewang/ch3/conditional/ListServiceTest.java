package com.bogewang.ch3.conditional;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by bogewang on 2017/7/10.
 */
public class ListServiceTest {
    @Test
    public void showListCmd() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ListService listService = context.getBean(ListService.class);
        System.out.println(context.getEnvironment().getProperty("os.name") + "系统下,显示目录命令为: " + listService.showListCmd());
    }

}