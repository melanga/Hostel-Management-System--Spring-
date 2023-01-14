package com.myapp.hostel_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends User {
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "hostel_id")
    private Hostel hostel;

    public Student() {
    }

    public Hostel getHostel() {
        return hostel;
    }

    public void setHostel(Hostel hostel) {
        this.hostel = hostel;
    }
}
