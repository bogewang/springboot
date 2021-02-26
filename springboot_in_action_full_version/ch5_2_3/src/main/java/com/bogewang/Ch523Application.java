package com.bogewang;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication      //1
public class Ch523Application {
    @Value("${book.author}")
    private String bookAutor;

    @Value("${book.name}")
    private String bookname;

	public static void main(String[] args) {
		SpringApplication.run(Ch523Application.class, args);
	}

	@RequestMapping("/")
	String index(){
        return "bookname is:" + bookname + " and bookauthor is: " + bookAutor;
    }
}
