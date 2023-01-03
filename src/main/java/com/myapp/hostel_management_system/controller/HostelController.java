package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.repository.HostelRepository;
import com.myapp.hostel_management_system.service.HostelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/hostels")
public class HostelController {

    private HostelService hostelService;

    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @GetMapping("/hostels")
    public String getAllHostels(Model model) {
        model.addAttribute("hostels",hostelService.getAllHostel());
        return "hostel/index";
    }

    @GetMapping("/hostels/add")
    public String createHostel(Model model) {
        Hostel hostel = new Hostel();
        model.addAttribute("hostels",hostelService.getAllHostel());
        return "hostel/addHostel";
    }

    @PostMapping("/hostels")
    public String saveHostel(@ModelAttribute("hostels") Hostel hostel, BindingResult result) {
        hostelService.hostelSave(hostel);
        return "redirect:/hostels";
    }


    @GetMapping("{id}")
    public String getAHostel(Model model, @PathVariable Long id) {
//        var result = hostelService.findById(id);
//        if (!result.isEmpty()) {
//            Hostel hostel = result.get();
//            model.addAttribute(hostel);
//        }
        return "hostel/view";
    }
}
