package com.project.controller;

import com.project.Util.MD5Util;
import com.project.pojo.Client;
import com.project.pojo.req.UserReq;
import com.project.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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

        String code = null;
        try {
            code = (String) redisTemplate.opsForValue().get(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }

        if (code == null || !code.equals(userReq.getCode())){
            return "fail";
        }

        Client client = new Client();

        // Client密码MD5加密
        client.setPass(md5Util.pass2MD5(userReq.getPass()));
        client.setMail(userReq.getMail());
        client.setIdc(userReq.getIdc());
        client.setName(userReq.getName());
        client.setSex(userReq.getSex() == null || userReq.getSex() == "" ? "male" : userReq.getSex());
        client.setPid(userReq.getPid());
        System.out.println(client);

        if (clientService.saveClient(client) >= 1){
            return "success";
        }
        return "fail";
    }

    @GetMapping("/getAllClient")
    public List<Client> getAllClient() {
        return clientService.finaAll();
    }

    @PostMapping("/loginClient")
    public String loginClient(@RequestBody Map<String, Object> map){

        List<Client> list = clientService.findByIdcOrMail((String) map.get("idc"), (String) map.get("mail"));

        if (list.isEmpty()){
            return "no";
        }

        if (md5Util.pass2MD5((String)map.get("pass")).equals(list.get(0).getPass())){
            return "success";
        }

        return "fail";
    }
}
