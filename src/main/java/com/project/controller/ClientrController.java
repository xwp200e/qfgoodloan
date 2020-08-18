package com.project.controller;

import com.project.Util.MD5Util;
import com.project.pojo.Client;
import com.project.pojo.req.UserReq;
import com.project.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class ClientrController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private RedisTemplate redisTemplate;

    private MD5Util md5Util = MD5Util.getInstance();

    @PostMapping("/registerClient")
    public String registerClient(@RequestBody UserReq userReq){

        String mail = userReq.getMail();

        String code =(String)redisTemplate.opsForValue().get(mail);
        if (code == null || !code.equals(userReq.getCode())){
            return "fail";
        }

        Client client = new Client();

        // Client密码MD5加密
        client.setPass(md5Util.pass2MD5(userReq.getPass()));
        client.setIdc(userReq.getIdc());
        client.setName(userReq.getName());
        client.setSex(userReq.getSex() == null || userReq.getSex() == "" ? "male" : userReq.getSex());
        client.setPid(userReq.getPid());


        if (clientService.saveClient(client) >= 1){
            return "success";
        }
        return "fail";
    }

    @GetMapping("/getAllClient")
    public List<Client> getAllClient() {
        return clientService.finaAll();
    }
}
