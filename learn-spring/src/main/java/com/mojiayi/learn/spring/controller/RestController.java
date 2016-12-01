package com.mojiayi.learn.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value = "/rest")
public class RestController {
	@RequestMapping(value = "/restEntrance", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody JSONObject restEntrance(HttpServletRequest request) {
		JSONObject respObj = new JSONObject();
		System.out.println("access restEntrance");
		return respObj;
	}
}
