package com.myapp.hostel_management_system.repository;

import com.myapp.hostel_management_system.entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostelRepository extends JpaRepository<Hostel, String> {

}
