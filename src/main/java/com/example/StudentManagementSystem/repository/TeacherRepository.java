package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Optional<Teacher> findByIdentityNumber(String identityNumber);
}
