package com.example.hrms.repository;

import com.example.hrms.model.*;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE username = #{username}")
    User getUserByUsername(String username);

    @Insert("INSERT INTO Users(username, password, department_id, role_id, is_supervisor, status) VALUES(#{username}, #{password}, #{departmentId}, #{roleId}, #{isSupervisor}, #{status})")
    void insertUser(User user);

    @Update("UPDATE Users SET password = #{password}, department_id = #{departmentId}, role_id = #{roleId}, is_supervisor = #{isSupervisor}, status = #{status} WHERE username = #{username}")
    void updateUser(User user);

    @Delete("DELETE FROM Users WHERE username = #{username}")
    void deleteUser(String username);
}