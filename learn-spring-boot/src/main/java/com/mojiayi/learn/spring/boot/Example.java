package com.mojiayi.learn.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/")
public class Example {
	@RequestMapping("home")
	String home() {
		return "Hello World!这里是home";
	}
	
	@RequestMapping("applist")
	String appList() {
		return "Hello World!这里是Applist";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Example.class, args);
	}

}
