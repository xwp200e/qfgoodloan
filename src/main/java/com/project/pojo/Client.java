package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    private int cid;
    private String idc;
    private String photo;
    private String pass;
    private String name;
    private String sex;
    private int status;
    private int lid;
    private int pid;

}
