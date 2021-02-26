package com.bogewang.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by bogewang on 2017/8/18.
 */
@Configuration
@EnableWebSocketMessageBroker //1 开启使用stomp协议来传输基于代理(message broker 的消息, 这是控制器支持使用@messageMapping, 就像使用@RequestMapping一样;
public class WebSockConfig extends AbstractWebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {    //2 注册stomp协议的节点,并映射URL
        registry.addEndpoint("/endpointWisely").withSockJS();   //3 注册一个stomp的endpoint, 并制定使用socketJS协议;
        registry.addEndpoint("/endpointChat").withSockJS();     //6 注册一个名为 endpointChat 的消息代理;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {    //4 配置消息代理
        registry.enableSimpleBroker("/queue","/topic");      //5 广播式应配置一个/topic 消息代理,点对点式应增加一个/queue消息代理;
    }
}
