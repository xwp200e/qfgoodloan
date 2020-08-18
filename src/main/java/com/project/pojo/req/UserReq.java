package com.project.pojo.req;

import lombok.Data;

@Data
public class UserReq {

    private String mail;
    private String idc;
    private String pass;
    private String name;
    private String sex;
    private int pid;
    private String code;
}
