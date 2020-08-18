package com.project.service.impl;

import com.project.dao.ClientDao;
import com.project.pojo.Client;
import com.project.service.IClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {

    @Resource
    private ClientDao clientDao;

    @Override
    public List<Client> finaAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findByid(int cid) {
        Optional<Client> byId = clientDao.findById(cid);
        if (byId.isPresent()){
            return byId.get();
        }
        return null;
    }


    @Override
    public Integer updateClient(Client e) {
        Client client = clientDao.saveAndFlush(e);
        if (client != null){
            return 1;
        }
            return 0;
    }


    @Override
    public void saveClient(Client e) {
        clientDao.saveAndFlush(e);
    }

    @Override
    public void deleteClient(int cid) {
        clientDao.deleteById(cid);
    }


}
