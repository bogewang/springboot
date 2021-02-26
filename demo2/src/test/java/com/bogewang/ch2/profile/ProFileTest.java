package com.bogewang.ch2.profile;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by bogewang on 2017/7/7.
 */
public class ProFileTest {

    @Test
    public void profileTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");     //1 先将活动profile设置为prod
        context.register(ProfileConfig.class);      //2 后置注册Bean配置类,不然会报Bean未定义的错误;
        context.refresh();          //3 刷新容器;
        DemoBean demoBean = context.getBean(DemoBean.class);
        System.out.println(demoBean.getContent());
        context.close();
    }
}
