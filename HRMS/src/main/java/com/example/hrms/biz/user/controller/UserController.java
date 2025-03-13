package com.example.hrms.biz.user.controller;

import com.example.hrms.biz.user.model.User;
import com.example.hrms.biz.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user != null ? user : null;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/searchUsers")
    public List<User> searchUsers(
            @RequestParam(required = false) List<Long> departmentIds,
            @RequestParam(required = false) List<String> roles) {
        return userService.searchUsers(departmentIds, roles);
    }

    @PostMapping("/insertUser")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }

    @PutMapping("/updateUser/{username}")
    public void updateUser(@PathVariable String username, @RequestBody User user) {
        user.setUsername(username);
        userService.updateUser(user);
    }

    @DeleteMapping("/deleteUser/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}