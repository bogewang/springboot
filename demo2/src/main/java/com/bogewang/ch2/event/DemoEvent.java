package com.bogewang.ch2.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by bogewang on 2017/7/7.
 */
public class DemoEvent extends ApplicationEvent {
    private String msg;
    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
