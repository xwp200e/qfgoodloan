package com.project.dao;

import com.project.pojo.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProfessionDao extends JpaRepository<Profession, Serializable> {
}
