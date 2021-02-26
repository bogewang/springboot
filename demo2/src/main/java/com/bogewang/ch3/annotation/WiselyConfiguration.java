package com.bogewang.ch3.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Created by bogewang on 2017/7/10.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration      //1 组合Configuration 元注解
@ComponentScan      //2 组合@ComponentScan 元注解
public @interface WiselyConfiguration {
    String[] value() default {};     //3 覆盖vlaue值;
}
