package com.bogewang.ch3.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bogewang on 2017/7/10.
 */
@Configuration
public class ConditionConfig {
    @Bean
    @Conditional(WindowsCondition.class)        //1 符合windows条件则实例化,windowsListService
    public ListService windowsListService(){
        return new WindowsListService();
    }

    @Bean
    @Conditional(LinuxCondition.class)      //2 符合linux条件则实例化,LinuxListService
    public ListService linuxListService(){
        return new LinuxListServiceImpl();
    }
}
