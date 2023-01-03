package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String home(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        if (user == null) {
            return "redirect:/login";
        }
        return "app/home";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "app/error";
    }
}
