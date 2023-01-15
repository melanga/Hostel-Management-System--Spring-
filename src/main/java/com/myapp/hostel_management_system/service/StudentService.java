package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.repository.HostelRepository;
import com.myapp.hostel_management_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final HostelRepository hostelRepository;

    public StudentService(StudentRepository studentRepository, HostelRepository hostelRepository) {
        this.studentRepository = studentRepository;
        this.hostelRepository = hostelRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void studentSave(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).get();
    }

    public boolean updateStudent(Student student) {
        Student existingStudent = studentRepository.getReferenceById(student.getId());
        existingStudent.setFirstname(student.getFirstname());
        existingStudent.setLastname(student.getLastname());
        existingStudent.setEmail(student.getEmail());
        if (student.getHostel() != existingStudent.getHostel()) {
            if (hostelRepository.countAllByStudents(existingStudent.getHostel()) <= existingStudent.getHostel().getCapacity()) {
                existingStudent.setHostel(student.getHostel());
            } else {
                return false;
            }
        }
        studentRepository.save(existingStudent);
        return true;
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
