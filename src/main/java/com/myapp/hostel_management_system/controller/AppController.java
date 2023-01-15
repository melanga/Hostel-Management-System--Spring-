package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.entity.User;
import com.myapp.hostel_management_system.service.AuthorizeService;
import com.myapp.hostel_management_system.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    private final AuthorizeService authorizeService;
    private final StudentService studentService;

    public AppController(AuthorizeService authorizeService, StudentService studentService) {
        this.authorizeService = authorizeService;
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String home(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        if (!authorizeService.isAuthorized()) {
            return "redirect:/login";
        } else {
            if (user instanceof Student) {
                var student = studentService.getStudent(user.getId());
                model.addAttribute("user", student);
                return "app/student/index";
            } else {
                return "redirect:/admin-students";
            }
        }
    }
}
