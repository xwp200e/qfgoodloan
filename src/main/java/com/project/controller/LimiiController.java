package com.project.controller;


import com.project.Util.CreditUtils;
import com.project.pojo.Client;
import com.project.pojo.Limii;
import com.project.pojo.Loaned;
import com.project.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class LimiiController {


    @Resource
    private IClientService clientService;

    @Resource
    private ILimiiService limiiService;

    @Resource
    private ILoanecStatuService loanecStatuService;
    private ICreditService creditService;



    @RequestMapping("/queryLimit")
    public List<Integer> queryLimit(HttpServletRequest request){
        int cid = (int) request.getSession().getAttribute("cid");

        Client c = clientService.findByid(cid);
        int l = c.getLid();
        Limii limii = limiiService.findBylid(l);
        List<Integer> list = new ArrayList<>();

        // 添加贷款总额度！
        list.add(limii.getQuota());
        //剩余贷款额度
        int money = 0;
        List<Loaned> allLoanedByCidNotTrue = loanecStatuService.getAllLoanedByCidNotTrue(cid);
        for (Loaned loaned : allLoanedByCidNotTrue) {
            money += loaned.getRmoney();
        }
        list.add(money);

        return list;
    }


    // 购买信用值 i
    @RequestMapping("/buyLimit")
    public String buyLimit(int i, HttpServletRequest request) {
        // 获取用户的client
        int cid = (int) request.getSession().getAttribute("cid");
        Client client = clientService.findByid(cid);
        // 获取用户信用分 并加上购买的分数
        int score = creditService.findByCid(cid).getScore() + i;
        // 通过信用转换额度等级工具类
        int limit = CreditUtils.getInstance().getCredit(score);
        // 增加用户额度等级
        client.setLid(limit);

        return "success";
    }

}
