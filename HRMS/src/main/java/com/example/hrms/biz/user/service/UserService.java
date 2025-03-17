package com.example.hrms.biz.user.service;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.biz.user.model.criteria.UserCriteria;
import com.example.hrms.biz.user.model.dto.UserDTO;
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

    public List<User> searchUsers(UserCriteria criteria) {
        return userMapper.searchUsers(criteria.getDepartmentIds(), criteria.getRoles());
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

    public int count(UserCriteria criteria) {
        return userMapper.count(criteria);
    }

    public List<UserDTO.Resp> list(UserCriteria criteria) {
        List<User> users = searchUsers(criteria);
        return users.stream()
                .map(user -> {
                    UserDTO.Resp resp = new UserDTO.Resp();
                    resp.setUsername(user.getUsername());
                    resp.setDepartmentId(user.getDepartmentId());
                    resp.setRole(user.getRole());
                    resp.setIsSupervisor(user.isSupervisor());
                    resp.setStatus(user.getStatus());
                    return resp;
                })
                .collect(Collectors.toList());
    }

    // Thêm phương thức để lấy đơn vị và quyền của tất cả người dùng
    public List<UserDTO.DepartmentAndRole> getDepartmentsAndRoles() {
        List<User> users = userMapper.getDepartmentsAndRoles();
        return users.stream()
                .map(user -> {
                    UserDTO.DepartmentAndRole resp = new UserDTO.DepartmentAndRole();
                    resp.setDepartmentId(user.getDepartmentId());
                    resp.setRole(user.getRole());
                    return resp;
                })
                .collect(Collectors.toList());
    }
}