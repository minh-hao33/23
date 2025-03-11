package com.example.hrms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@MapperScan({"com.example.hrms.biz.booking.repository",
        "com.example.hrms.biz.department.repository",
        "com.example.hrms.biz.user.repository",
        "com.example.hrms.biz.request.repository",
        "com.example.hrms.biz.role.repository",
        "com.example.hrms.biz.meetingroom.repository",
        "com.example.hrms.biz.sample.repository"})
public class HrmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

//    public static void main(String[] args) {
//        try {
//            String url = "jdbc:mysql://localhost:3306/hrms";
//            String username = "root";
//            String password = "Sa@123456";
//
//            // Establish connection
//            Connection connection = DriverManager.getConnection(url, username, password);
//            if (connection != null) {
//                System.out.println("Connection successful!");
//
//                // Create a statement
//                Statement statement = connection.createStatement();
//
//                // SQL SELECT query
//                String sql = "SELECT room_id, room_name, location, capacity FROM meeting_rooms";
//
//                // Execute the query
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                // Process the result set
//                while (resultSet.next()) {
//                    Long roomId = resultSet.getLong("room_id");
//                    String roomName = resultSet.getString("room_name");
//                    String location = resultSet.getString("location");
//                    int capacity = resultSet.getInt("capacity");
//
//                    System.out.println("Room ID: " + roomId +
//                            ", Room Name: " + roomName +
//                            ", Location: " + location +
//                            ", Capacity: " + capacity);
//                }
//
//                // Close the resources
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } else {
//                System.out.println("Failed to connect.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
