package com.project.service.impl;

import com.project.dao.LoanedDao;
import com.project.pojo.Loaned;
import com.project.service.ILoanedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class LoanedServiceImpl implements ILoanedService {


    @Resource
    private LoanedDao loanedDao;

    @Override
    public List<Loaned> findAll() {
        return loanedDao.findAll();
    }

    @Override
    public Loaned findByloid(int loid) {
        Optional<Loaned>byId = loanedDao.findById(loid);
        if (byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public Integer updateLoaned(Loaned e) {
        Loaned loaned = loanedDao.saveAndFlush(e);
        if (loaned != null){
            return 1;
        }
             return 0;
    }

    @Override
    public Integer saveLoaned(Loaned e) {
        Loaned loaned = loanedDao.saveAndFlush(e);
        if (loaned != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteLoaned(int loid) {
        try{
            loanedDao.deleteById(loid);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
