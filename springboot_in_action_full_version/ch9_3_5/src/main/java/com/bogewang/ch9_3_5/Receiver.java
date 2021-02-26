package com.bogewang.ch9_3_5;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息监听
 */
@Component
public class Receiver {
    @RabbitListener(queues =  "my-queue")
    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
    }
}
