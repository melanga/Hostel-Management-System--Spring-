package com.myapp.hostel_management_system.repository;

import com.myapp.hostel_management_system.entity.Warden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardenRepository extends JpaRepository<Warden, String> {
}
