package com.bogewang.ch6_2_3;

import com.bogewang.ch6_2_3.config.AuthorSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Ch623Application {
    @Autowired
    private AuthorSetting authorSetting;        //1 直接注入 该配置

	public static void main(String[] args) {
		SpringApplication.run(Ch623Application.class, args);
	}

	@RequestMapping("/")
	public String index(){
	    return "author name is:" + authorSetting.getName() + " and author age is:" + authorSetting.getAge();
    }

}
