package com.example.hrms.biz.department.repository;

import com.example.hrms.biz.department.model.Department;
import com.example.hrms.biz.department.model.criteria.DepartmentCriteria;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Insert("INSERT INTO Departments (department_name) VALUES (#{departmentName})")
    @Options(useGeneratedKeys = true, keyProperty = "departmentId")
    void insertDepartment(Department department);

    @Select("""
    SELECT 
        d.department_id AS departmentId, 
        d.department_name AS departmentName, 
        u.employee_name AS employeeName, 
        r.role_name AS roleName 
    FROM Departments d 
    LEFT JOIN Users u ON u.department_id = d.department_id 
    LEFT JOIN Roles r ON u.role_name = r.role_name 
    ORDER BY d.department_name ASC, r.role_name ASC
""")
    List<Department> findAllDepartmentsWithNullableUserInfo();
    @Select("""
    SELECT 
        d.department_id AS departmentId,
        d.department_name AS departmentName,
        u.employee_name AS employeeName,
        r.role_name AS roleName
    FROM Users u
    JOIN Departments d ON u.department_id = d.department_id
    JOIN Roles r ON u.role_name = r.role_name
    WHERE u.department_id = (SELECT department_id FROM Users WHERE username = #{username})
    ORDER BY d.department_name ASC, r.role_name ASC
""")
    List<Department> findByDepartmentOfUser(@Param("username") String username);

    // Tìm phòng ban theo ID
    @Select("""
    SELECT 
        d.department_id AS departmentId, 
        d.department_name AS departmentName, 
        u.employee_name AS employeeName,
        r.role_name AS roleName 
    FROM Departments d 
    LEFT JOIN Users u ON d.department_id = u.department_id 
    LEFT JOIN Roles r ON u.role_name = r.role_name 
    WHERE d.department_id = #{id} 
    ORDER BY r.role_name ASC
""")
    List<Department> findById(Long id);


    // Đếm số lượng phòng ban theo tên (loại trừ ID hiện tại)
    @Select("SELECT COUNT(*) FROM Departments WHERE department_name = #{departmentName} AND (#{departmentId} IS NULL OR department_id != #{departmentId})")
    int countByName(@Param("departmentName") String departmentName, @Param("departmentId") Long departmentId);

    @Update("UPDATE Departments SET department_name = #{departmentName} WHERE department_id = #{departmentId}")
    void updateDepartment(Department department);

    @Delete("DELETE FROM Departments WHERE department_id = #{departmentId}")
    void deleteDepartment(Long departmentId);
    @Update("UPDATE Users SET department_id = NULL WHERE department_id = #{departmentId}")
    void updateUsersToNull(Long departmentId);

}