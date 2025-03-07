package com.example.hrms.biz.role.controller;

import com.example.hrms.biz.role.model.Role;
import com.example.hrms.biz.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public void insertRole(@RequestBody Role role) {
        roleService.insertRole(role);
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setRoleId(id);
        roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}