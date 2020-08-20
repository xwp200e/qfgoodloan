package com.project.controller;

import com.project.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    IEmailService emailService;


    @GetMapping("/sendMail")
    public Integer sendMail(@RequestParam("mail")String mail){
        return emailService.sendMail(mail);
    }
}
