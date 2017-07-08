package com.mojiayi.learn.spring.controller;

import com.mojiayi.learn.spring.bean.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/8.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @RequestMapping(value = "/whatisthetime", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public SimpleResponse showSimpleResponse() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new SimpleResponse(HttpStatus.OK.value(), "Now is " + format.format(new Date()));
    }
}
