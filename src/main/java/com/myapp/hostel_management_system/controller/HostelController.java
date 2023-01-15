package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.service.AuthorizeService;
import com.myapp.hostel_management_system.service.HostelService;
import com.myapp.hostel_management_system.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/hostels")
public class HostelController {

    private final HostelService hostelService;
    private final StudentService studentService;
    private final AuthorizeService authorizeService;

    public HostelController(HostelService hostelService, StudentService studentService, AuthorizeService authorizeService) {
        this.hostelService = hostelService;
        this.studentService = studentService;
        this.authorizeService = authorizeService;
    }

    @GetMapping("/hostels")
    public String getAllHostels(Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("hostels", hostelService.getAllHostel());
            return "hostel/index";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/hostels/add")
    public String createHostel(Model model) {
        if (authorizeService.warden()) {
            Hostel hostel = new Hostel();
            model.addAttribute("hostels", hostelService.getAllHostel());
            return "hostel/addHostel";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/hostels")
    public String saveHostel(Hostel hostel, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                hostelService.hostelSave(hostel);
                redirectAttributes.addFlashAttribute("success", "Hostel added successfully");
                return "redirect:/hostels";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/hostels/add";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/hostels/edit/{id}")
    public String EditHostel(@PathVariable String id, Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("hostel", hostelService.getHostelById(id));
            return "hostel/edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/hostels/{id}")
    public String updateHostel(@PathVariable String id,
                               @ModelAttribute("hostels") Hostel hostel,
                               RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                Hostel existingHostel = hostelService.getHostelById(id);
                existingHostel.setId(id);
                existingHostel.setName(hostel.getName());
                existingHostel.setAddress(hostel.getAddress());
                existingHostel.setCapacity(hostel.getCapacity());
                hostelService.updateHostel(existingHostel);
                redirectAttributes.addFlashAttribute("success", "Hostel updated successfully");
                return "redirect:/hostels";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/hostels";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/hostels/{id}")
    public String deleteHostel(@PathVariable String id, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                hostelService.deleteHostelById(id);
                redirectAttributes.addFlashAttribute("success", "Hostel deleted successfully");
                return "redirect:/hostels";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/hostels";
            }
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/hostels/{hostel_id}/student/{std_id}")
    public String addStudentToHostel(@PathVariable String hostel_id,
                                     @ModelAttribute("hostels") Hostel hostel,
                                     Model model, @PathVariable String std_id) {
        if (authorizeService.warden()) {
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
        } else {
            return "redirect:/";
        }
    }
}
