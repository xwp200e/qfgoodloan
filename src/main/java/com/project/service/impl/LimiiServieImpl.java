package com.project.service.impl;

import com.project.dao.LimiiDao;
import com.project.pojo.Limii;
import com.project.service.ILimiiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class LimiiServieImpl implements ILimiiService {

    @Resource
    private LimiiDao limitDao;

    @Override
    public List<Limii> findAll() {
        return limitDao.findAll();
    }

    @Override
    public Limii findBylid(int lid) {
        Optional<Limii>byId = limitDao.findById(lid);
        if (byId.isPresent()){
           return byId.get();
        }
        return null;
    }

    @Override
    public Integer updateLoaned(Limii e) {
        Limii limit = limitDao.saveAndFlush(e);
        if (limit != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer saveLimit(Limii e) {
        Limii limit = limitDao.saveAndFlush(e);
        if (limit != null){
            return 1;
        }
        return null;
    }

    @Override
    public Integer deleteLimit(int lid) {
        try{
            limitDao.deleteById(lid);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
