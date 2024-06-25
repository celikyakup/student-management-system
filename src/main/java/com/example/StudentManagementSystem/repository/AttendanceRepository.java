package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    Optional<Attendance> findByLessonIdAndStudentId(Long lessonId,Long StudentId);
}
