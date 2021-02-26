package com.bogewang.ch1.di;

import com.bogewang.ch1.di.config.DiConfig;
import com.bogewang.ch1.di.service.UseFunctionService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/6.
 */
public class DiConfigTest {
    @Test
    public void testBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DiConfig.class);
        UseFunctionService useFunctionService = context.getBean(UseFunctionService.class);
        System.out.println(useFunctionService.sayHello("di"));
        context.close();
    }
}
