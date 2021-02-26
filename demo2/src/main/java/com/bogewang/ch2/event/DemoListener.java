package com.bogewang.ch2.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by bogewang on 2017/7/7.
 */
@Component
public class DemoListener implements ApplicationListener<DemoEvent> {       //1 指定监听事件类型;
    @Override
    public void onApplicationEvent(DemoEvent demoEvent) {       //2 使用onApplicationEvent方法对消息进行接受处理;
        String msg = demoEvent.getMsg();
        System.out.println("我(bean-demoListener)接收到了bean-demoPublisher发布的消息" + msg);
    }
}
