package com.example.StudentManagementSystem.dto.request;

import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamRequest {
    private String examName;

    private Double score;

    private Student student;

    private Lesson lesson;
}
