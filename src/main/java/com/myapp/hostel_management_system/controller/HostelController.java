package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.repository.HostelRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hostel")
public class HostelController {
    private final HostelRepository hostelRepository;

    public HostelController(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    @GetMapping
    public String getAllHostels(Model model) {
        var hostels = hostelRepository.findAll();
        model.addAttribute(hostels);
        return "hostel/index";
    }

    @GetMapping("/add")
    public String viewAddHostel() {
        return "hostel/addHostel";
    }

    @PostMapping("/add")
    public String createHostel(Hostel hostel) {
        hostelRepository.save(hostel);
        return "hostel/index";
    }

    @GetMapping()
    public String getAHostel(@RequestParam(name = "id") Long id, Model model) {
        var result = hostelRepository.findById(id);
        if (!result.isEmpty()) {
            Hostel hostel = result.get();
            model.addAttribute(hostel);
        }
        return "hostel/view";
    }
}
