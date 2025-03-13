package com.example.hrms.biz.role.repository;

import com.example.hrms.biz.role.model.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("SELECT role_id, role_name FROM Roles WHERE role_id = #{roleId}")
    Role getRoleById(Long roleId);

    @Select("SELECT role_id, role_name FROM Roles")
    List<Role> getAllRoles();

    @Insert("INSERT INTO Roles(role_name) VALUES(#{roleName})")
    void insertRole(Role role);

    @Update("UPDATE Roles SET role_name = #{roleName} WHERE role_id = #{roleId}")
    void updateRole(Role role);

    @Delete("DELETE FROM Roles WHERE role_id = #{roleId}")
    void deleteRole(Long roleId);
}