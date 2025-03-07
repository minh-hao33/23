package com.example.hrms.biz.user.repository;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.enumation.RoleEnum;
import com.example.hrms.enumation.StatusEnum;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM Users WHERE username = #{username}")
    @Results({
            @Result(property = "role", column = "role", javaType = RoleEnum.class),
            @Result(property = "status", column = "status", javaType = StatusEnum.class)
    })
    User getUserByUsername(String username);

    @Insert("INSERT INTO Users(username, password, department_id, role, is_supervisor, status) VALUES(#{username}, #{password}, #{departmentId}, #{role}, #{isSupervisor}, #{status})")
    void insertUser(User user);

    @Update("UPDATE Users SET password = #{password}, department_id = #{departmentId}, role = #{role}, is_supervisor = #{isSupervisor}, status = #{status} WHERE username = #{username}")
    void updateUser(User user);

    @Delete("DELETE FROM Users WHERE username = #{username}")
    void deleteUser(String username);
}