package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Hostel;
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

     public Student getStudentById(String id){
          return studentRepository.findById(id).get();
     }
     public Student updateStudent(Student student){
          return studentRepository.save(student);
     }
     public void deleteStudentById(String id){
          studentRepository.deleteById(id);
     }
}
