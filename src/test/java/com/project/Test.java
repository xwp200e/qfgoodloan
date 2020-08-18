package com.project;

import com.project.pojo.Credit;
import com.project.pojo.Loaned;
import com.project.service.ICreditService;
import com.project.service.ILoanedService;
import com.project.service.IProfessionService;
import com.project.service.impl.ProfessionServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {


    @Resource
    IProfessionService iProfessionService;
    @org.junit.Test
    public void aatest(){
       // IProfessionService ss = new ProfessionServiceImpl();
       // System.out.println(ss.findAll());
        System.out.println(iProfessionService.findAll());
    }
    @Resource
    IProfessionService ProfessionService;
    @org.junit.Test
    public void bbbtest(){
        System.out.println(iProfessionService.findBypid(2));
    }

    @Resource
    ICreditService creditService;
    @org.junit.Test
    public void  ccctest(){
        System.out.println(creditService.finaAll());
    }

    @Resource
    ICreditService wcreditService;
    @org.junit.Test
    public void dddtest(){
        System.out.println(creditService.findByid(2));
    }
    @Resource
    ICreditService w2creditService;
    @org.junit.Test
    public void eeetest(){
        Credit c = new Credit(3,3,40);

        System.out.println(creditService.saveCredit(c));
    }
    @Resource
    ILoanedService loanedService;
    @org.junit.Test
    public void ffftest(){
        System.out.println(loanedService.findAll());
    }

    @Resource
    ILoanedService wloanedService;
    @org.junit.Test
    public void gggtest(){
        System.out.println(loanedService.findByloid(2));
    }

}
