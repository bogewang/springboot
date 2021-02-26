package com.bogewang.ch3.annotation;

import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/10.
 */
@Service
public class DemoService {

    public void outputResult(){
        System.out.println("从组合注解照样获取的Bean");
    }
}
