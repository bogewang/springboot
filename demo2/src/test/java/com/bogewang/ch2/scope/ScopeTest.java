package com.bogewang.ch2.scope;

import com.bogewang.ch2.scope.DemoPrototypeService;
import com.bogewang.ch2.scope.DemoSingletonService;
import com.bogewang.ch2.scope.ScopeConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/6.
 */
public class ScopeTest {
    @Test
    public void scopeTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScopeConfig.class);
        DemoSingletonService s1 = context.getBean(DemoSingletonService.class);
        DemoSingletonService s2 = context.getBean(DemoSingletonService.class);
        DemoPrototypeService p1 = context.getBean(DemoPrototypeService.class);
        DemoPrototypeService p2 = context.getBean(DemoPrototypeService.class);
        System.out.println("s1 与 s2 是否相等: " + s1.equals(s2));
        System.out.println("p1 与 p2 是否相等: " + p1.equals(p2));
        context.close();

    }
}
