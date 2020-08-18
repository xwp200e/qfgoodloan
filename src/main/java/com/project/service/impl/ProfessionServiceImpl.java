package com.project.service.impl;

import com.project.dao.ProfessionDao;
import com.project.pojo.Profession;
import com.project.service.IProfessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessionServiceImpl implements IProfessionService {

    @Resource
    private ProfessionDao professionDao;

    @Override
    public List<Profession> findAll() {
        return professionDao.findAll();
    }

    @Override
    public Profession findBypid(int pid) {
        Optional<Profession>byId = professionDao.findById(pid);
        if (byId.isPresent()){
            return byId.get();
        }
        return null;
    }

    @Override
    public Integer updateProfession(Profession e) {
        Profession profession = professionDao.saveAndFlush(e);
        if (profession != null){
            return 1;
        }
            return 0;
    }

    @Override
    public Integer saveProfession(Profession e) {
        Profession profession = professionDao.saveAndFlush(e);
        if (profession != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteProfession(int pid) {
        try{
            professionDao.deleteById(pid);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
