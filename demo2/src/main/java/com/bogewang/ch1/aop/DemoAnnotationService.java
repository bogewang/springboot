package com.bogewang.ch1.aop;

import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/6.
 */
@Service
public class DemoAnnotationService {
    @Action(name="注解式拦截的add操作")
    public void add(){}
}
