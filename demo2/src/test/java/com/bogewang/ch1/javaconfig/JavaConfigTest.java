package com.bogewang.ch1.javaconfig;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/6.
 */
public class JavaConfigTest {
    @Test
    public void testJavaConfigTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("javaconfig"));
        context.close();
    }
}
