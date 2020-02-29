package com.joey.community.community.service;

import com.joey.community.community.mapper.UserMapper;
import com.joey.community.community.model.User;
import com.joey.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public User findUserByAccountId(String accountId){

        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(accountId);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size() != 0) {
            return users.get(0);
        }
        return null;
    }
    public void insert(User user) {
        userMapper.insert(user);
    }
    public void update(User realUser) {
        userMapper.updateByPrimaryKeySelective(realUser);

    }
}

