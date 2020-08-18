package com.project.service;


import com.project.pojo.Profession;

import java.util.List;

public interface IProfessionService {

    List<Profession>findAll();

    Profession findBypid(int pid);

    Integer updateProfession(Profession e);

    Integer saveProfession(Profession e);

    Integer deleteProfession(int pid);
}
