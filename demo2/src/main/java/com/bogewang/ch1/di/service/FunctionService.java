package com.bogewang.ch1.di.service;

import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/6.
 */
@Service
public class FunctionService {
    public String sayHello(String word){
        return "Hello " + word + "!";
    }
}
