package com.project.service;

import com.project.pojo.Loaned;

import java.util.List;

public interface ILoanedService {

    List<Loaned>findAll();

    Loaned findByloid(int loid);

    boolean updateLoaned(Loaned e);

    Integer saveLoaned(Loaned e);

    Integer deleteLoaned(int loid);
}
