package com.mojiayi.learn.spring.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/normal")
public class NormalController {
	@RequestMapping(value = "/normalEntrance", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String normalEntrance(HttpServletRequest request) {
		System.out.println("access normalEntrance");
		return "/normal";
	}
}
