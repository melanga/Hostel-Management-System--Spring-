package com.myapp.hostel_management_system.controller;

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
//@RequestMapping("/admin")
public class StudentController {
    private final StudentService studentService;
    private final HostelService hostelService;

    public StudentController(StudentService studentService, HostelService hostelService) {
        super();
        this.studentService = studentService;
        this.hostelService = hostelService;
    }

    @GetMapping("/admin-students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());

        return "app/admin/student/index";
    }

    @GetMapping("/admin-students/add")
    public String createStudents(Model model) {
        Student student = new Student();
        model.addAttribute("students", studentService.getAllStudents());

        return "app/admin/student/add";
    }

    @PostMapping("/admin-students")
    public String saveStudent(@ModelAttribute("students") Student student, BindingResult result) {
        if (student.getPassword() == null) {
            student.setPassword("abcd1234");
        }
        studentService.studentSave(student);
        return "redirect:/admin-students";
    }

    @GetMapping("/admin-students/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        model.addAttribute("hostels", hostelService.getAllHostel());
        return "app/admin/student/edit";
    }

    @PostMapping("/admin-students/{id}")
    public String updateStudent(@PathVariable String id,
                                @ModelAttribute("students") Student student) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setHostel(student.getHostel());
        studentService.updateStudent(existingStudent);
        return "redirect:/admin-students";
    }

    @GetMapping("/admin-students/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
        return "redirect:/admin-students";

    }

}
