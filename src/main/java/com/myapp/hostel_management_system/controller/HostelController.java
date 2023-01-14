package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.service.HostelService;
import com.myapp.hostel_management_system.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping("/hostels")
public class HostelController {

    private final HostelService hostelService;
    private final StudentService studentService;

    public HostelController(HostelService hostelService, StudentService studentService) {
        this.hostelService = hostelService;
        this.studentService = studentService;
    }

    @GetMapping("/hostels")
    public String getAllHostels(Model model) {
        model.addAttribute("hostels", hostelService.getAllHostel());
        return "hostel/index";
    }

    @GetMapping("/hostels/add")
    public String createHostel(Model model) {
        Hostel hostel = new Hostel();
        model.addAttribute("hostels", hostelService.getAllHostel());
        return "hostel/addHostel";
    }

    @PostMapping("/hostels")
    public String saveHostel(Hostel hostel, BindingResult result) {
        hostelService.hostelSave(hostel);
        return "redirect:/hostels";
    }

    @GetMapping("/hostels/edit/{id}")
    public String EditHostel(@PathVariable String id, Model model) {
        model.addAttribute("hostels", hostelService.getHostelById(id));
        return "hostel/edit";
    }

    @PostMapping("/hostels/{id}")
    public String updateHostel(@PathVariable String id,
                               @ModelAttribute("hostels") Hostel hostel,
                               Model model) {
//        get hostel from db
        Hostel existingHostel = hostelService.getHostelById(id);
        existingHostel.setId(id);
        existingHostel.setName(hostel.getName());
        existingHostel.setAddress(hostel.getAddress());
        existingHostel.setCapacity(hostel.getCapacity());
        //Save to db
        hostelService.updateHostel(existingHostel);
        return "redirect:/hostels";
    }

    @GetMapping("/hostels/{id}")
    public String deleteHostel(@PathVariable String id) {
        hostelService.deleteHostelById(id);
        return "redirect:/hostels";
    }

    @PostMapping("/hostels/{hostel_id}/student/{std_id}")
    public String addStudentToHostel(@PathVariable String hostel_id,
                                     @ModelAttribute("hostels") Hostel hostel,
                                     Model model, @PathVariable String std_id) {
        Hostel existingHostel = hostelService.getHostelById(hostel_id);
        Student student = studentService.getStudentById(std_id);
        if (student.getHostel() == null) {
            studentService.addHostel(student, existingHostel);
            model.addAttribute("msg", "Student added to " +
                    existingHostel.getName() + " hostel successfully");
        } else {
            studentService.addHostel(student, existingHostel);
            model.addAttribute("msg", "Student updated to " +
                    existingHostel.getName() + " hostel successfully");
        }

        return "redirect:/hostels";
    }
}
