package com.joey.community.community.service;

import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findUserByAccountId(String accountId){
        User user = userMapper.findUserByAccountId(accountId);
        return user;
    }

    public void insert(User user) {
        userMapper.insert(user);
    }

    public void update(User realUser) {
        userMapper.update(realUser);
    }
}

