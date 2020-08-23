package com.project.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loaned {
    @Id
    private int loid;
    private int cid;
    private int lmoney;
    private Date ltime;
    private int month;
    private int rmoney;
    private boolean lstatus;
    private String subject;
    private boolean ischecked;

}
