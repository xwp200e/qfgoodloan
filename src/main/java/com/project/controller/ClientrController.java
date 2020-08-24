package com.project.controller;

import com.project.Util.MD5Util;
import com.project.pojo.Client;
import com.project.pojo.Credit;
import com.project.pojo.req.UserReq;
import com.project.service.IClientService;

import com.project.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
public class ClientrController {

    @Autowired
    private IClientService clientService;
    @Autowired
    private ICreditService creditService;

    @Autowired
    private RedisTemplate redisTemplate;

    private MD5Util md5Util = MD5Util.getInstance();


    // 注册Client
    @PostMapping("/registerClient")
    public String registerClient(@RequestBody UserReq userReq, HttpServletRequest request){

        String mail = userReq.getMail();

        String code = null;
        try {
            code = (String) redisTemplate.opsForValue().get(mail);
        } catch (Exception e) {
            e.printStackTrace();
            return "yanzhengmacuowu";
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

        // 设置初始额度
        switch (userReq.getPid()){
            case 1:
            case 0:
                client.setLid(1);
                break;
            default:
                client.setLid(2);
                break;
        }

        // 添加用户征信
        Credit credit = new Credit();
        credit.setCid(client.getCid());
        credit.setScore(60);
        creditService.saveCredit(credit);


        // 添加Client 并且把name，cid，photo放到session里
        if (clientService.saveClient(client) >= 1){
            request.getSession().setAttribute("name", client.getName());
            request.getSession().setAttribute("cid", client.getCid());
            request.getSession().setAttribute("photo", client.getPhoto());
            return "success";
        }
        return "fail";
    }

    @GetMapping("/getAllClient")
    public List<Client> getAllClient() {
        return clientService.finaAll();
    }

    // Client登录
    @PostMapping("/loginClient")
    public String loginClient(@RequestBody Map<String, Object> map, HttpServletRequest request){

        List<Client> list = clientService.findByIdcOrMail((String) map.get("idc"), (String) map.get("mail"));

        if (list.isEmpty()){
            return "no";
        }
        System.out.println(list.get(0).getCid());
        if (md5Util.pass2MD5((String)map.get("pass")).equals(list.get(0).getPass())){
            request.getSession().setAttribute("name", list.get(0).getName());
            request.getSession().setAttribute("cid", list.get(0).getCid());
            request.getSession().setAttribute("photo", list.get(0).getPhoto());
            return "success";
        }

        return "fail";
    }

    // 获取用户name
    @GetMapping("/getName")
    public String getName(HttpServletRequest request){

        return (String) request.getSession().getAttribute("name");
    }

    // 清空session
    @GetMapping("/sessionClear")
    public String sessionClear(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("cid");
        request.getSession().removeAttribute("photo");
        return "success";
    }
    
    @PostMapping("/updateClient")
    public String updateClient(@RequestBody Client client){

        Integer integer = clientService.updateClient(client);
        if (integer == 1){
            return "success";
        }

        return "fail";
    }
}
