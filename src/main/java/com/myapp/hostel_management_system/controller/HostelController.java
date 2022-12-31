package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.repository.HostelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hostel")
public class HostelController {
    private final HostelRepository hostelRepository;

    public HostelController(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    @GetMapping
    public List<Hostel> getAllHostels() {
        return hostelRepository.findAll();
    }
}
