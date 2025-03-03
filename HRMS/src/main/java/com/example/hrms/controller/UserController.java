package com.example.hrms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/home")
    public String index() {
        return "home";
    }
}
