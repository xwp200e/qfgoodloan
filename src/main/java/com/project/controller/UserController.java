package com.project.controller;

import com.project.pojo.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;


    @RequestMapping("/allUser")
    public List<User> getAllUser() {
        return userService.queryUserList();
    }
}
