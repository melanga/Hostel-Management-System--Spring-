package com.myapp.hostel_management_system.repository;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    int countAllByHostel(Hostel hostel);
}
