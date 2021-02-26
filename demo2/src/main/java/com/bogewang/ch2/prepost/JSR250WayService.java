package com.bogewang.ch2.prepost;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by bogewang on 2017/7/7.
 */
public class JSR250WayService {
    @PostConstruct  //1 在构造函数执行完之后执行;
    public void init(){
        System.out.println("jsr250-init-method");
    }

    public JSR250WayService() {
        super();
        System.out.println("初始化构造函数-JSR250WayService");
    }

    @PreDestroy //2 在Bean销毁之前执行;
    public void destory(){
        System.out.println("jsr250-destory-method");
    }
}
