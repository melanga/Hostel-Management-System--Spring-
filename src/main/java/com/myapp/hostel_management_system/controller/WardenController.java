package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Warden;
import com.myapp.hostel_management_system.repository.WardenRepository;
import com.myapp.hostel_management_system.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/warden")
public class WardenController {
    @Autowired
    WardenRepository wardenRepository;
    @Autowired
    AuthorizeService authorizeService;


    @GetMapping("")
    public String listWardens(Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("wardens", wardenRepository.findAll());
            return "app/admin/warden/index";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/add")
    public String createWardens() {
        if (authorizeService.warden()) {
            return "app/admin/warden/add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add")
    public String saveWarden(@ModelAttribute("students") Warden warden, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                if (warden.getPassword() == null) {
                    warden.setPassword("abcd1234");
                }
                if (warden.getEmail().endsWith("@uwu.ac.lk")) {
                    wardenRepository.save(warden);
                    redirectAttributes.addFlashAttribute("success", "Warden added successfully");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Only university provided emails are supported");
                }
                return "redirect:/warden";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/warden";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String editWarden(@PathVariable String id, Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("warden", wardenRepository.getWardenById(id));
            return "app/admin/warden/edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateWarden(@PathVariable String id,
                               @ModelAttribute("students") Warden warden, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                warden.setId(id);
                wardenRepository.save(warden);
                redirectAttributes.addFlashAttribute("success", "Warden updated successfully");
                return "redirect:/warden";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/warden";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                wardenRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("success", "Warden deleted successfully");
                return "redirect:/warden";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/warden";
            }
        } else {
            return "redirect:/";
        }
    }
}
