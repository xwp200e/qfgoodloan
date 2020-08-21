package com.project.controller;

import com.project.pojo.Loaned;
import com.project.service.ILoanecStatuService;
import com.project.service.ILoanedService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanedController {

    @Resource
    private ILoanedService loanedService;

    @Resource
    private ILoanecStatuService loanecStatuService;

    @ResponseBody
    @GetMapping("/updataLoaned")
    public String updataLoaned(Loaned loaned){
        if (loanedService.updateLoaned(loaned)){
            return "success";
        }
        return "failed";
    }

    @PostMapping("/queryLoaned")
    public List<Loaned> queryLoaned(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanedService.findAllByCid(cid);
        return loanedList;
    }

    @PostMapping("/queryLoanedNotTrue")
    public List<Loaned> queryLoanedNotTrue(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanecStatuService.getAllLoanedByCidNotTrue(cid);
        return loanedList;
    }

    @PostMapping("/queryLoanedTrue")
    public List<Loaned> queryLoanedTrue(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanecStatuService.getAllLoanedByCidTrue(cid);
        return loanedList;
    }
}
