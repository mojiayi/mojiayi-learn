package com.mojiayi.learn.spring.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mojiayi.learn.spring.bean.User;
import com.mojiayi.learn.spring.view.View;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    @JsonView(View.Summary.class)
    public List<User> getAllMessages() {
        List<User> list = new ArrayList<>();
        User user = new User(123L, "first", "last");
        list.add(user);
        return list;
    }
}
