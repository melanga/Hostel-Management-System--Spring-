package com.myapp.hostel_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String viewLogin() {
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "app/home";
    }
}
