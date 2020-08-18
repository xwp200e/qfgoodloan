package com.project.service;



import com.project.pojo.Credit;

import java.util.List;

public interface ICreditService {
    List<Credit> finaAll();

    Credit findByid(int crid);

    Integer updateCredit(Credit e);

    Integer saveCredit(Credit e);

    Integer deleteCredit(int crid);
}
