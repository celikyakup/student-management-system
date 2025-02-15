package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam,Long> {

    Optional<Exam> findByStudentIdAndLessonId(Long studentId,Long lessonId);
}
