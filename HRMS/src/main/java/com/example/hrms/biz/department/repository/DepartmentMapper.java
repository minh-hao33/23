package com.example.hrms.biz.department.repository;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Insert("INSERT INTO Departments (department_id, department_name, role_id) VALUES (#{departmentId}, #{departmentName}, #{roleId})")
    void insertDepartment(Department department);

    @Select("SELECT d.department_id AS departmentId, d.department_name AS departmentName, d.role_id AS roleId, u.username AS userName, r.role_name AS roleName " +
            "FROM Users u " +
            "JOIN Departments d ON u.department_id = d.department_id " +
            "JOIN Roles r ON d.role_id = r.role_id")
    @Results({
            @Result(property = "departmentId", column = "departmentId"),
            @Result(property = "departmentName", column = "departmentName"),
            @Result(property = "roleId", column = "roleId"),
            @Result(property = "userName", column = "userName"),
            @Result(property = "roleName", column = "roleName")
    })
    List<Department> selectAll();

    @Select("SELECT COUNT(*) FROM Departments")
    int count(DepartmentCriteria criteria);

    @Update("UPDATE Departments SET department_name = #{departmentName}, role_id = #{roleId} WHERE department_id = #{departmentId}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM Users WHERE department_id = #{departmentId}")
    void deleteUsersByDepartmentId(Long departmentId);

    @Delete("DELETE FROM Departments WHERE department_id = #{departmentId}")
    void deleteDepartment(Long departmentId);
}