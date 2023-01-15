package com.myapp.hostel_management_system.controller;

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
//@RequestMapping("/admin")
public class StudentController {
    private final StudentService studentService;
    private final HostelService hostelService;

    private final AuthorizeService authorizeService;

    public StudentController(StudentService studentService, HostelService hostelService, AuthorizeService authorizeService) {
        this.studentService = studentService;
        this.hostelService = hostelService;
        this.authorizeService = authorizeService;
    }

    @GetMapping("/admin-students")
    public String listStudents(Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("students", studentService.getAllStudents());
            return "app/admin/student/index";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin-students/add")
    public String createStudents(Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("students", studentService.getAllStudents());
            return "app/admin/student/add";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/admin-students")
    public String saveStudent(@ModelAttribute("students") Student student, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                if (student.getPassword() == null) {
                    student.setPassword("abcd1234");
                }
                if (student.getEmail().endsWith("@std.uwu.ac.lk")) {
                    studentService.studentSave(student);
                    redirectAttributes.addFlashAttribute("success", "Student added successfully");
                    return "redirect:/admin-students";
                } else {
                    redirectAttributes.addFlashAttribute("error", "Only university provided emails are supported");
                    return "redirect:/admin-students";
                }
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/admin-students";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin-students/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        if (authorizeService.warden()) {
            model.addAttribute("student", studentService.getStudentById(id));
            model.addAttribute("hostels", hostelService.getAllHostel());
            return "app/admin/student/edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/admin-students/{id}")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute("students") Student student, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                if (studentService.updateStudent(student)) {
                    redirectAttributes.addFlashAttribute("success", "Student updated successfully");
                } else {
                    redirectAttributes.addFlashAttribute("error", "hostel is full");
                }
                return "redirect:/admin-students";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/admin-students";
            }
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/admin-students/{id}")
    public String deleteStudent(@PathVariable String id, RedirectAttributes redirectAttributes) {
        if (authorizeService.warden()) {
            try {
                studentService.deleteStudentById(id);
                redirectAttributes.addFlashAttribute("success", "Student deleted successfully");
                return "redirect:/admin-students";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "an error occurred");
                return "redirect:/admin-students";
            }
        } else {
            return "redirect:/";
        }
    }
}
