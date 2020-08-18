package com.project.dao;

import com.project.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


public interface ClientDao extends JpaRepository<Client, Serializable> {
}
