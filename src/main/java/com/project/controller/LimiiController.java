package com.project.controller;


import com.project.pojo.Client;
import com.project.pojo.Limii;
import com.project.service.IClientService;
import com.project.service.ILimiiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LimiiController {


    @Resource
    private IClientService clientService;

    @Resource
    private ILimiiService limiiService;



    @PostMapping("/queryLimit")
    public int queryLimit(HttpServletRequest request){
        int cid = (int) request.getSession().getAttribute("cid");

        Client c = clientService.findByid(cid);
        int l = c.getLid();
        Limii limii = limiiService.findBylid(l);

        return limii.getQuota();
    }

}
