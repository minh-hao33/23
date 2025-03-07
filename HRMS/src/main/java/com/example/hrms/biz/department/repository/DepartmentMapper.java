package com.example.hrms.biz.department.repository;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.main.department.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM Departments WHERE department_id = #{departmentId}")
    Department getDepartmentById(Long departmentId);

    @Insert("INSERT INTO Departments(department_name, role_id) VALUES(#{departmentName}, #{roleId})")
    void insertDepartment(Department department);

    @Update("UPDATE Departments SET department_name = #{departmentName}, role_id = #{roleId} WHERE department_id = #{departmentId}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM Departments WHERE department_id = #{departmentId}")
    void deleteDepartment(Long departmentId);
}