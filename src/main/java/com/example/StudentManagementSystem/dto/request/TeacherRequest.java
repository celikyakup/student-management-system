package com.example.StudentManagementSystem.dto.request;

import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRequest {

    private String name;

    private String surname;

    private String identityNumber;

    private LocalDate birthDate;

    private Gender gender;

    private List<Lesson> lessonList;

    private User user;
}
