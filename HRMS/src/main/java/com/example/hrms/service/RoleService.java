package com.example.hrms.service;

import com.example.hrms.model.*;
import com.example.hrms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public Role getRoleById(Long roleId) {
        return roleMapper.getRoleById(roleId);
    }

    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleMapper.deleteRole(roleId);
    }
}