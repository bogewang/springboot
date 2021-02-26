package com.bogewang.ch2;

import com.bogewang.ch2.event.DemoPublisher;
import com.bogewang.ch2.event.EventConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/7.
 */
public class EventTest {
    @Test
    public void eventTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EventConfig.class);
        DemoPublisher publisher = context.getBean(DemoPublisher.class);
        publisher.publish("hello application event!");
        context.close();
    }
}
