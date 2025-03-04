package com.example.hrms.biz.main.user.service;

import com.example.hrms.biz.main.user.model.User;
import com.example.hrms.biz.main.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(String username) {
        userMapper.deleteUser(username);
    }
}