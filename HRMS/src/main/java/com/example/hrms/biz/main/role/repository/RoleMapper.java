package com.example.hrms.biz.main.role.repository;

import com.example.hrms.biz.main.role.model.Role;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper {
    @Select("SELECT * FROM Roles WHERE role_id = #{roleId}")
    Role getRoleById(Long roleId);

    @Insert("INSERT INTO Roles(role_name) VALUES(#{roleName})")
    void insertRole(Role role);

    @Update("UPDATE Roles SET role_name = #{roleName} WHERE role_id = #{roleId}")
    void updateRole(Role role);

    @Delete("DELETE FROM Roles WHERE role_id = #{roleId}")
    void deleteRole(Long roleId);
}