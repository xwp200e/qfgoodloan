package com.project.service.impl;

import com.project.dao.CreditDao;
import com.project.pojo.Credit;
import com.project.service.ICreditService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements ICreditService {

    @Resource
    private CreditDao cc;
    @Override
    public List<Credit> finaAll() {
        return cc.findAll();
    }

    @Override
    public Credit findByid(int crid) {
        Optional<Credit> byId = cc.findById(crid);
        if (byId.isPresent()){
            return byId.get();
        }

        return null;
    }

    @Override
    public Integer updateCredit(Credit e) {
        Credit credit = cc.saveAndFlush(e);
        if (credit !=null){
            return 1;
        }
             return 0;
    }

    @Override
    public Integer saveCredit(Credit e) {
        Credit credit = cc.saveAndFlush(e);
        if (credit != null){
            return 1;
        }
             return 0;
    }

    @Override
    public Integer deleteCredit(int crid) {
        try {
            cc.deleteById(crid);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
