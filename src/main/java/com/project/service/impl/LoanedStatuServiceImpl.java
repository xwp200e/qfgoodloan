package com.project.service.impl;

import com.project.dao.LoanedStatuDao;
import com.project.pojo.Loaned;
import com.project.service.ILoanecStatuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoanedStatuServiceImpl implements ILoanecStatuService {

    @Resource
    private LoanedStatuDao loanedStatuDao;

    @Override
    public List<Loaned> getAllLoanedByCidTrue(int cid) {
        return loanedStatuDao.getAllLoanedByCidTrue(cid);
    }

    @Override
    public List<Loaned> getAllLoanedByCidNotTrue(int cid) {
        return loanedStatuDao.getAllLoanedByCidNotTrue(cid);
    }
}
