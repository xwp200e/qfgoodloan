package com.project.dao;

import com.project.pojo.Loaned;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface LoanedDao extends JpaRepository<Loaned, Serializable> {
}
