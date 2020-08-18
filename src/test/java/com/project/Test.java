package com.project;

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



}
