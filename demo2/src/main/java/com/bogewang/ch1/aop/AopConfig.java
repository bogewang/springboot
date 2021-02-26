package com.bogewang.ch1.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by bogewang on 2017/7/6.
 */
@Configuration
@ComponentScan("com.bogewang.ch1.aop")
@EnableAspectJAutoProxy //注解开启spring对AspectJ代理的支持;
public class AopConfig {

}
