package com.project.service;

import com.project.pojo.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICompanyService {
    List<Company> finaAll();

    Company findByid(int cpid);

    Integer updateCredit(Company company);

    Integer saveCredit(Company company);

    Integer deleteCredit(int cpid);

}
