package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.repository.HostelRepository;
import com.myapp.hostel_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    @Autowired
    private HostelRepository hostelRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student studentSave(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).get();
    }

    public void updateStudent(Student student) {
        Student existingStudent = studentRepository.getReferenceById(student.getId());
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setHostel(student.getHostel());
        studentRepository.save(existingStudent);
    }

    public void deleteStudentById(String id) {
        studentRepository.deleteById(id);
    }

    public void addHostel(Student student, Hostel hostel) {
        student.setHostel(hostel);
        studentRepository.save(student);
    }

    public Student getStudent(String id) {
        return studentRepository.findById(id).get();
    }
}
