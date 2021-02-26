package com.bogewang.ch3.taskexecutor;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by bogewang on 2017/7/10.
 */
@Service
public class AsyncTaskService {

    @Async      //1 该方法是个异步方法,如果注解在类,则表明该类所有方法都是异步方法;
    public void executeAsyncTask(Integer i){
        System.out.println("执行异步任务: " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i){
        System.out.println("执行异步任务+1: " + (i + 1));
    }
}
