package com.example.hrms.biz.department.repository;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT COUNT(*) FROM Departments d " +
            "JOIN Roles r ON d.role_id = r.role_id " +
            "WHERE d.department_name LIKE CONCAT('%', #{search}, '%')")
    int count(DepartmentCriteria criteria);

    @Select("SELECT d.department_id AS departmentId, d.department_name AS departmentName, d.role_id AS roleId, r.role_name AS roleName " +
            "FROM Departments d " +
            "JOIN Roles r ON d.role_id = r.role_id " +
            "WHERE d.department_name LIKE CONCAT('%', #{search}, '%')")
    List<Department> select(DepartmentCriteria criteria);

    @Insert("INSERT INTO Departments(department_name, role_id) VALUES(#{departmentName}, #{roleId})")
    void insertDepartment(Department department);

    @Update("UPDATE Departments SET department_name = #{departmentName}, role_id = #{roleId} WHERE department_id = #{departmentId}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM Departments WHERE department_id = #{departmentId}")
    void deleteDepartment(Long departmentId);
}