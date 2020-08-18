package com.project.service.impl;

import com.project.dao.UserDao;
import com.project.pojo.User;
import com.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    public UserDao userDao;

    @Override
    public List<User> queryUserList() {
        return userDao.queryAllUser();
    }
}
