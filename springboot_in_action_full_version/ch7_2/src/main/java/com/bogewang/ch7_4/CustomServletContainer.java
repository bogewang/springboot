package com.bogewang.ch7_4;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by bogewang on 2017/8/18.
 */
@Component
public class CustomServletContainer implements EmbeddedServletContainerCustomizer{
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //container.setPort(8888);    //1.设置端口
        //container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/404.html"));   //2.设置错误页面;
        //container.setSessionTimeout(10, TimeUnit.MINUTES);      //3.设置会话过期时间
    }
}
