package com.project.controller;

import com.project.pojo.Client;
import com.project.pojo.Credit;
import com.project.pojo.Limii;
import com.project.pojo.Loaned;
import com.project.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class LoanedController {

    @Resource
    private ICreditService creditService;

    @Resource
    private ILoanedService loanedService;

    @Resource
    private ILoanecStatuService loanecStatuService;
    @Resource
    private ILimiiService limiiService;
    @Resource
    private IClientService clientService;

    @ResponseBody
    @GetMapping("/updataLoaned")
    public String updataLoaned(Loaned loaned){
        if (loanedService.updateLoaned(loaned)){
            return "success";
        }
        return "failed";
    }

    @GetMapping("/queryLoaned")
    public List<Loaned> queryLoaned(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanedService.findAllByCid(cid);
        return loanedList;
    }

    @GetMapping("/queryLoanedNotTrue")
    public List<Loaned> queryLoanedNotTrue(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanecStatuService.getAllLoanedByCidNotTrue(cid);
        return loanedList;
    }

    @GetMapping("/queryLoanedTrue")
    public List<Loaned> queryLoanedTrue(HttpServletRequest request){
        int cid = (int)request.getSession().getAttribute("cid");
        List<Loaned> loanedList = loanecStatuService.getAllLoanedByCidTrue(cid);
        return loanedList;
    }

    @GetMapping("/saveLoaned")
    public String saveLoaed(Loaned loaned, HttpServletRequest request) {

        int cid = (int) request.getSession().getAttribute("cid");
        Credit credit = creditService.findByCid(cid);


        // 统计目前所有欠款金额 并计算剩余贷款金额额度
        int money = 0;
        List<Loaned> allLoanedByCidNotTrue = loanecStatuService.getAllLoanedByCidNotTrue(cid);
        for (Loaned l : allLoanedByCidNotTrue) {
            money += l.getRmoney();
        }
        Client c = clientService.findByid(cid);
        int l = c.getLid();
        Limii limii = limiiService.findBylid(l);
        int i = limii.getQuota() - money;

        // 信用分低于60 或者 未还的贷款大于5笔 或者  申请的贷款大于现存额度  不给贷款
        if (credit.getScore() < 60 || loanecStatuService.getAllLoanedByCidNotTrue(cid).size() > 5 || loaned.getLmoney() > i){
            return "fail";
        }
        loaned.setIschecked(false);
        loaned.setRmoney(loaned.getLmoney());
        loaned.setLtime(new java.sql.Date(new Date().getTime()));
        Integer integer = loanedService.saveLoaned(loaned);
        if (integer == 1){
            return "success";
        }

        return "fail";
    }
}
