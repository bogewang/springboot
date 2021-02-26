package com.bogewang.ch1.aop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/6.
 */
public class AopTest {
    @Test
    public void aopTest(){
        AnnotationConfigApplicationContext cont = new AnnotationConfigApplicationContext(AopConfig.class);
        DemoAnnotationService demoAnnotationService = cont.getBean(DemoAnnotationService.class);
        DemoMethodService demoMethodService = cont.getBean(DemoMethodService.class);
        demoAnnotationService.add();
        demoMethodService.add();
        cont.close();
    }
}
