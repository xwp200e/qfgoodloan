package com.project.service.impl;

import com.project.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements IEmailService {
    @Value("${spring.mail.username}")
    private String sendName;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Integer sendMail(String mail) {
        Random r = new Random();
        String random = "" + r.nextInt(9999);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sendName);
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject("千锋好分期验证码");
        simpleMailMessage.setText("您的验证码是：" + random);
        try {
            javaMailSender.send(simpleMailMessage);
            redisTemplate.opsForValue().set(mail,random);
            redisTemplate.expire(mail,300, TimeUnit.SECONDS);
            return 1;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }

    }
}
