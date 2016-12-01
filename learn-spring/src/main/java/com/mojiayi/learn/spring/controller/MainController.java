package com.mojiayi.learn.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		System.out.println("get in index");
		return "/index";
	}

	@RequestMapping(value = "/f/{recommend}", method = RequestMethod.GET)
	public String fromNormalRecommend(Model model, @PathVariable("recommend") String recommend) {
		System.out.println("fromRecommend,f=" + recommend);
		model.addAttribute("recommendId", "f=" + recommend);
		return "/index";
	}

	/*
	 * @RequestMapping(value = "/{recommend}", method = RequestMethod.GET)
	 * public @ResponseBody JSONObject fromAgentRecommend(Model
	 * model, @PathVariable("recommend") String recommend) {
	 * System.out.println("fromRecommend,s=" + recommend);
	 * model.addAttribute("recommendId", "s=" + recommend); //return
	 * "/recommend"; return new JSONObject(); }
	 */

	@RequestMapping(value = "/{recommend}", method = RequestMethod.GET)
	public String fromAgentRecommend(HttpServletRequest request, Model model, @PathVariable("recommend") String recommend) {
		System.out.println("fromRecommend,s=" + recommend);
		model.addAttribute("recommendId", "s=" + recommend);
		return "/index";
	}
}
