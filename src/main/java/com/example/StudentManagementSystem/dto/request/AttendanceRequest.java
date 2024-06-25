package com.example.StudentManagementSystem.dto.request;

import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {

    private LocalDate date;

    private boolean present;

    private Student student;

    private Lesson lesson;

}
