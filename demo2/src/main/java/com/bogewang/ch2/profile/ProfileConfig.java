package com.bogewang.ch2.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by bogewang on 2017/7/7.
 */
@Configuration
public class ProfileConfig {

    @Bean
    @Profile("dev")     //1 profile 为dev时实例化devDemoBean ,开发的时候返回的bean;
    public DemoBean devDemoBean(){
        return new DemoBean("from development profile");
    }

    @Bean
    @Profile("prod")        //2 prod时实例化prodDemoBean ,生产的时候返回的bean
    public DemoBean prodDemoBean(){
        return new DemoBean("from production profile");
    }
}
