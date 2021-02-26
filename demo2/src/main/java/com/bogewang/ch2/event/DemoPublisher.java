package com.bogewang.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by bogewang on 2017/7/7.
 * 事件发布类;
 */
@Component
public class DemoPublisher {
    @Autowired
    ApplicationContext context;     //1 注入applicationContext 用来发布事件;

    public void publish(String msg){
        context.publishEvent(new DemoEvent(this,msg));  //2 使用publishEven来发布;
    }
}
