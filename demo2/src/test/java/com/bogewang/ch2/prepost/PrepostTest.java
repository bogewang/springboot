package com.bogewang.ch2.prepost;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/7.
 */
public class PrepostTest {
    @Test
    public void prepostTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrePostConfig.class);
        context.getBean(BeanWayService.class);
        context.getBean(JSR250WayService.class);
        context.close();
    }
}
