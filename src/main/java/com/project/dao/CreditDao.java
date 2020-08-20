package com.project.dao;

import com.project.pojo.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CreditDao extends JpaRepository<Credit, Serializable> {

    Credit findByCid(int cid);
}
