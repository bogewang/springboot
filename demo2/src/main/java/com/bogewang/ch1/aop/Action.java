package com.bogewang.ch1.aop;

import java.lang.annotation.*;

/**
 * Created by bogewang on 2017/7/6.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    String name();
}
