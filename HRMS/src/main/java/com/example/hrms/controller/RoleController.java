package com.example.hrms.controller;

import com.example.hrms.model.*;
import com.example.hrms.service.*;
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