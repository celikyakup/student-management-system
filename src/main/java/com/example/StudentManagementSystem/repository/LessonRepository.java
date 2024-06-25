package com.example.StudentManagementSystem.repository;

import com.example.StudentManagementSystem.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson,Long> {
    Optional<Lesson> findByLessonCodeAndLessonStartDateAndLessonEndDate(String lessonCode, LocalDateTime lessonStartDate,LocalDateTime lessonEndDate);
}
