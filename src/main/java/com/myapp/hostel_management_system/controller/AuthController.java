package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.entity.User;
import com.myapp.hostel_management_system.repository.StudentRepository;
import com.myapp.hostel_management_system.repository.UserRepository;
import com.myapp.hostel_management_system.repository.WardenRepository;
import com.myapp.hostel_management_system.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    AuthorizeService authorizeService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    WardenRepository wardenRepository;

    @GetMapping("/register")
    public String register() {
        if (authorizeService.isAuthorized()) {
            return "redirect:/home";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(Student student, Model model) {
        try {
            if (student.getEmail().endsWith("@std.uwu.ac.lk")) {
                studentRepository.save(student);
                model.addAttribute("msg", "Account created successfully");
            } else {
                model.addAttribute("error", "Enter university email address");
            }
        } catch (Exception e) {
            model.addAttribute("error", "There was an error processing the request\n" +
                    "try again later");
        }
        return "auth/register";
    }

    @GetMapping("/login")
    public String viewLogin() {
        if (authorizeService.isAuthorized()) {
            return "redirect:/home";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginUser(User user, Model model, HttpSession session) {
        User dbUser = userRepository.getReferenceByEmail(user.getEmail());
        if (dbUser != null) {
            if (dbUser.getPassword().equals(user.getPassword())) {
                System.out.println("Login success");
                model.addAttribute("msg", "Welcome " + dbUser.getFirstname() + " " + dbUser.getLastname());
                session.setAttribute("user", dbUser);
                return "redirect:/home";
            } else {
                model.addAttribute("error", "Invalid password");
            }
        } else {
            model.addAttribute("error", "Invalid email");
        }
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        request.getSession().invalidate();
        return "app/home";
    }
}
