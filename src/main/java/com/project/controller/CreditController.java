package com.project.controller;

import com.project.pojo.Credit;
import com.project.service.ICreditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class CreditController {

    @Resource
    private ICreditService creditService;


    @PostMapping("/queryCredit")
    public int queryCredit(HttpServletRequest request){
        int cid = (int) request.getSession().getAttribute("cid");
        Credit credit = creditService.findByCid(cid);

        return credit.getScore();
}
}
