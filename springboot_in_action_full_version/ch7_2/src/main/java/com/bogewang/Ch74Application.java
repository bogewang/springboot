package com.bogewang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 使用两种不同的方式,配置端口,错误页面,session时间;
 */
public class Ch74Application {

    public static void main(String[] args) {
        SpringApplication.run(Ch74Application.class, args);
    }

    @Component
    public static class CustomServletContainer implements EmbeddedServletContainerCustomizer{

        /**
         * Customize the specified {@link ConfigurableEmbeddedServletContainer}.
         *
         * @param container the container to customize
         */
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8080);        //设置端口
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));      //设置错误页面
            container.setSessionTimeout(10, TimeUnit.MINUTES);      //设置回话过期时间;10分钟

        }
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainerCustomizer(){
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(8080);
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        return factory;
    }
}
