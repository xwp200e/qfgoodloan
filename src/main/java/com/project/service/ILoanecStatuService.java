package com.project.service;

import com.project.dao.LoanedStatuDao;
import com.project.pojo.Loaned;

import javax.annotation.Resource;
import java.util.List;

public interface ILoanecStatuService {

    List<Loaned> getAllLoanedByCidTrue(int cid);

    List<Loaned> getAllLoanedByCidNotTrue(int cid);
}
