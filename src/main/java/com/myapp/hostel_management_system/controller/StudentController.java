package com.myapp.hostel_management_system.controller;

import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/admin")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }

//    @PostMapping("/add")
//    publ

    @GetMapping("/admin-students")
    public String listStudents(Model model){
        model.addAttribute("students",studentService.getAllStudents());

        return "app/admin/student/index";
    }

    @GetMapping("/admin-students/add")
    public String createStudents(Model model){
        Student student = new Student();
        model.addAttribute("students",studentService.getAllStudents());

        return "app/admin/student/add";
    }

    @PostMapping("/admin-students")
    public String saveStudent(@ModelAttribute("students") Student student, BindingResult result){
//        System.out.println("Stude"+student);
        studentService.studentSave(student);
        return "redirect:/admin-students";
    }

}
