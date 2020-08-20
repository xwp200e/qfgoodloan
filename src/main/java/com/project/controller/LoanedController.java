package com.project.controller;

import com.project.pojo.Loaned;
import com.project.service.ILoanedService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoanedController {

    @Resource
    private ILoanedService loanedService;

    @ResponseBody
    @GetMapping("/updataLoaned")
    public String updataLoaned(Loaned loaned){
        if (loanedService.updateLoaned(loaned)){
            return "success";
        }
        return "failed";
    }

    @PostMapping("/queryLoaned")
    public int queryLoaned(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        Loaned l = loanedService.findByloid(cid);
        int i = l.getCid();
        Loaned loaned = loanedService.findByloid(i);
        return l.getRmoney();
    }
}
