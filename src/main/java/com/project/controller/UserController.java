package com.project.controller;

import com.project.Util.MD5Util;
import com.project.pojo.Client;
import com.project.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IClientService clientService;

    private MD5Util md5Util = MD5Util.getInstance();

    @PostMapping("/registerClient")
    public String registerClient(Client client){
        // Client密码MD5加密
        client.setPass(md5Util.pass2MD5(client.getPass()));

        return null;
    }
}
