package com.project.controller;

import com.project.Util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile multipartFile){

        String originalFilename = multipartFile.getOriginalFilename();
        System.out.println(originalFilename);

        return qiniuUtils.upload(multipartFile);


    }
}
