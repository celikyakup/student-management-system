package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.Student;

import com.example.StudentManagementSystem.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByStudentNumber(String studentNumber);

}
