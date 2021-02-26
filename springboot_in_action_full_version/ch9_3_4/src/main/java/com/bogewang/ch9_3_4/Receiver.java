package com.bogewang.ch9_3_4;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = "my-destination")
    public void receiveMessage(String message){
        System.out.println("接收到:<" + message +">");
    }
}
