package com.project.dao;

import com.project.pojo.Loaned;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoanedStatuDao {

    List<Loaned> getAllLoanedByCidTrue(int cid);

    List<Loaned> getAllLoanedByCidNotTrue(int cid);

}
