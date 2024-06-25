package com.example.StudentManagementSystem.dto.response;
import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.Gender;

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
public class StudentResponse {

    private Long id;

    private String name;

    private String surname;

    private String studentNumber;

    private Integer classInfo;

    private LocalDate birthDate;

    private Gender gender;

    private User user;

    private List<Lesson> lessonList;
}
