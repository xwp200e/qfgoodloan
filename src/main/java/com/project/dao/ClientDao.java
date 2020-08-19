package com.project.dao;

import com.project.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;


public interface ClientDao extends JpaRepository<Client, Serializable> {

    List<Client> findByIdcOrMail(String idc, String mail);
}
