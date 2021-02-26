package com.bogewang.ch2.prepost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bogewang on 2017/7/7.
 */
@Configuration
@ComponentScan("com.bogewang.ch2.prepost")
public class PrePostConfig {
    @Bean(initMethod = "init",destroyMethod = "destory")    //1 initMethod 和 destoryMethod 指定BeanWayService 类的init和destroy 方法在构造之后,销毁之前;
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    @Bean
    JSR250WayService jsr250WayService(){
        return new JSR250WayService();
    }
}

