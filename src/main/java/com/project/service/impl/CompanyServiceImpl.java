package com.project.service.impl;

import com.project.dao.CompanyDao;
import com.project.pojo.Company;
import com.project.service.ICompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public List<Company> finaAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findByid(int cpid) {
        Optional<Company> byId = companyDao.findById(cpid);
        if (byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public Integer updateCredit(Company company) {
        Company company1 = companyDao.saveAndFlush(company);
        if (company != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer saveCredit(Company company) {
        Company company1 = companyDao.saveAndFlush(company);
        if (company != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteCredit(int cpid) {
        try {
            companyDao.deleteById(cpid);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

}
