package com.bogewang.ch1.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by bogewang on 2017/7/6.
 */
@Component  //1 让此切面成为spring容器管理的Bean
@Aspect //2 通过 @Aspect 注解申明一个切面
public class LogAspect {
    @Pointcut("@annotation(com.bogewang.ch1.aop.Action)")   //3 注解申明切点
    public void annotationPointCut(){}

    @After("annotationPointCut()")  //4 注解申明一个建言,并使用@PointCut定义的切点
    public void after(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
        System.out.println("注解式拦截:" + action.name());    //5 通过反射可获得注解上的属性,然后做一个日志相关的操作
    }

    @Before("execution(* com.bogewang.ch1.aop.DemoMethodService.*(..))")    //6 注解申明一个建言,此建言直接使用拦截规则作为参数;
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("方法拦截规则," + method.getName());
    }
}
