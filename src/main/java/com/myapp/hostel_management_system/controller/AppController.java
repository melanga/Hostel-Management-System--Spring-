package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.entity.User;
import com.myapp.hostel_management_system.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    final
    AuthorizeService authorizeService;

    public AppController(AuthorizeService authorizeService) {
        this.authorizeService = authorizeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String home(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        if (!authorizeService.isAuthorized()) {
            return "redirect:/login";
        } else {
            if (user instanceof Student) {
                return "app/student/index";
            } else {
                return "redirect:/admin-students";
            }
        }
    }
}
