package com.project.service;

import com.project.pojo.Limii;

import java.util.List;

public interface ILimiiService {
    List<Limii>findAll();

    Limii findBylid(int lid);

    Integer updateLoaned(Limii e);

    Integer saveLimit(Limii e);

    Integer deleteLimit(int lid);
}
