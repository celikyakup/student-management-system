package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
