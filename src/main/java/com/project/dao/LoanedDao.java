package com.project.dao;

import com.project.pojo.Loaned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface LoanedDao extends JpaRepository<Loaned, Serializable> {

    List<Loaned> findAllByCid(int cid);

}
