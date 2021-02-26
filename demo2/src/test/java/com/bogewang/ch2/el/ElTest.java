package com.bogewang.ch2.el;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/6.
 */
public class ElTest {
    @Test
    public void elTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceConfig.class);
        ElConfig elService = context.getBean(ElConfig.class);
        elService.outputResource();
        context.close();
    }
}
