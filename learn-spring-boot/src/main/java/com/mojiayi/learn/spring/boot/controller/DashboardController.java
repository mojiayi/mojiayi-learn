package com.mojiayi.learn.spring.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	@RequestMapping("")
	String dashboard() {
		return "Hello World!这里是dashboard";
	}
	
	@RequestMapping("home")
	String home() {
		return "Hello World!这里是home";
	}

	@RequestMapping("applist")
	String appList() {
		return "Hello World!这里是Applist";
	}
}
