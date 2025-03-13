package com.example.hrms.biz.user.repository;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.enumation.RoleEnum;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    // Lấy tất cả người dùng
    @Select("SELECT username, password, department_id, role, is_supervisor, status FROM Users")
    List<User> getAllUsers();

    // Lấy người dùng theo tên người dùng
    @Select("SELECT username, password, department_id, role, is_supervisor, status FROM Users WHERE username = #{username}")
    User getUserByUsername(String username);

    // Thêm người dùng mới
    @Insert("INSERT INTO Users(username, password, department_id, role, is_supervisor, status) VALUES(#{username}, #{password}, #{departmentId}, #{role}, #{isSupervisor}, #{status})")
    void insertUser(User user);

    // Cập nhật thông tin người dùng
    @Update("UPDATE Users SET password = #{password}, department_id = #{departmentId}, role = #{role}, is_supervisor = #{isSupervisor}, status = #{status} WHERE username = #{username}")
    void updateUser(User user);

    // Xóa người dùng theo tên người dùng
    @Delete("DELETE FROM Users WHERE username = #{username}")
    void deleteUser(String username);

    // Tìm kiếm người dùng dựa trên departmentIds và roles
    @Select("<script>" +
            "SELECT username, password, department_id, role, is_supervisor, status FROM Users " +
            "WHERE 1=1 " + // Điều kiện bắt buộc để xử lý các điều kiện tiếp theo
            "<if test='departmentIds != null and departmentIds.size() > 0'> " +
            "AND department_id IN " +
            "<foreach item='id' collection='departmentIds' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach> " +
            "</if> " +
            "<if test='roles != null and roles.size() > 0'> " +
            "AND role IN " +
            "<foreach item='role' collection='roles' open='(' separator=',' close=')'>" +
            "#{role.value}" + // Sử dụng giá trị của enum RoleEnum
            "</foreach> " +
            "</if> " +
            "</script>")
    List<User> searchUsers(@Param("departmentIds") List<Long> departmentIds, @Param("roles") List<RoleEnum> roles);
}