package com.bogewang.ch7_6.web;

import com.bogewang.ch7_6.entity.WiselyMessage;
import com.bogewang.ch7_6.entity.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by bogewang on 2017/8/18.
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;        //3 通过simpMessagingTemplate 向浏览器发送消息;

    @MessageMapping("/welcome")     //1 当浏览器向服务端发送请求时,通过@MessageMapping 映射 /welcome 这个地址,类似@RequMapping
    @SendTo("/topic/getResponse")       //2 当服务器有消息时,会对订阅了@SentTo中的路径的浏览器发送消息;
    public WiselyResponse say(WiselyMessage message) throws Exception{
        Thread.sleep(3000);
        return new WiselyResponse("Wecome, " + message.getName() + "!");
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg){ //4 在springMvc中, 可以直接在参数中获得principal, 中包含当期用户的信息;
        if (principal.getName().equals("wyf")){ //5 硬编码,指定消息的发送用户和接收用户
            messagingTemplate.convertAndSendToUser("wisely","/queue/notifications",principal.getName() + "-send:"
            + msg);     // 6 第一个参数是接收消息的用户, 第二个是浏览器订阅的地址, 第三个是消息本身;
        } else {
            messagingTemplate.convertAndSendToUser("wyf","/queue/notifications",principal.getName() + "-send:" + msg);
        }
    }

}
