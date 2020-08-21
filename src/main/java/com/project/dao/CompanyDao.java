package com.project.dao;

import com.project.pojo.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


public interface CompanyDao extends JpaRepository<Company, Serializable>{


}
