package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.User;
import com.myapp.hostel_management_system.repository.StudentRepository;
import com.myapp.hostel_management_system.repository.UserRepository;
import com.myapp.hostel_management_system.repository.WardenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    WardenRepository wardenRepository;

    public AuthorizeService() {
    }

    public boolean isAuthorized() {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return userRepository.existsById(user.getId());
        }
        return false;
    }

    public boolean student() {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return studentRepository.existsById(user.getId());
        }
        return false;
    }

    public boolean warden() {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return wardenRepository.existsById(user.getId()) || !studentRepository.existsById(user.getId());
        }
        return false;
    }
}
