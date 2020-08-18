package com.project.service;

import com.project.pojo.Client;

import java.util.List;

public interface IClientService {
    List<Client> finaAll();

    Client findByid(int cid);

    Integer updateClient(Client e);

    Integer saveClient(Client e);

    Integer deleteClient(int cid);

}
