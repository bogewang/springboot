package com.bogewang.ch3.fortest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by bogewang on 2017/7/10.
 */
@Configuration
public class TestConfig {

    @Bean
    @Profile("prod")
    public TestBean testProdMod(){
        return new TestBean("prod");
    }

    @Bean
    @Profile("dev")
    public TestBean testDevMod(){
        return new TestBean("dev");
    }
}
