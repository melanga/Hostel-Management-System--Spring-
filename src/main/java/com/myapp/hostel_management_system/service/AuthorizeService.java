package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.User;
import com.myapp.hostel_management_system.repository.StudentRepository;
import com.myapp.hostel_management_system.repository.WardenRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    WardenRepository wardenRepository;

    public boolean student(User user) {

        if (request.getSession().getAttribute("user") != null) {
            User sessionUser = studentRepository.getReferenceById(user.getId());
            return sessionUser.getEmail() != null;
        }
        return false;
    }

    public boolean warden(User user) {
        if (request.getSession().getAttribute("user") != null) {
            User sessionUser = wardenRepository.getReferenceById(user.getId());
            return sessionUser.getEmail() != null;
        }
        return false;
    }
}
