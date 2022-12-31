package com.myapp.hostel_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User {
    public Student() {
    }
}
