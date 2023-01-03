package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {
     private StudentRepository studentRepository;

     public StudentService(StudentRepository studentRepository) {
          this.studentRepository = studentRepository;
     }

     public List<Student> getAllStudents(){
          return studentRepository.findAll();
     }

     public Student studentSave(Student student){
          return studentRepository.save(student);
     }
}
