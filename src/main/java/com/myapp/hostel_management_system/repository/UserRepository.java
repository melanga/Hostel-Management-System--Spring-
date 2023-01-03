package com.myapp.hostel_management_system.repository;

import com.myapp.hostel_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User getReferenceByEmail(String email);
}
