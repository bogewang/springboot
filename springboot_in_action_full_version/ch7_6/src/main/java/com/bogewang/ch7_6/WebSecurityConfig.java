package com.bogewang.ch7_6;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by bogewang on 2017/8/21.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login").permitAll()  //1 设置不拦截路径;
        .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")        //2 设置Spring Security的登录页面访问的路径为 login
        .defaultSuccessUrl("/chat")         //3 登录成功后转向路径;
        .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    //4 在内存中分别配置两个用户 wyf和wisely, 密码和用户名一致, 角色是USER;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("wyf").password("wyf").roles("USER")
                .and()
                .withUser("wisely").password("wisely").roles("USER");
    }

    // 指定静态资源, spring Security 不拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/static/**");
    }
}
