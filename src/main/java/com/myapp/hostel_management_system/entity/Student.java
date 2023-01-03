package com.myapp.hostel_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;

    public Student() {
    }
}
