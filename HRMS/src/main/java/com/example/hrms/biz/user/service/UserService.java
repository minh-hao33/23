package com.example.hrms.biz.user.service;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.enumation.RoleEnum;
import com.example.hrms.biz.user.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public List<User> searchUsers(List<Long> departmentIds, List<String> roleValues) {
        List<RoleEnum> roles = roleValues.stream()
                .map(RoleEnum::fromString)
                .collect(Collectors.toList());
        return userMapper.searchUsers(departmentIds, roles);
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