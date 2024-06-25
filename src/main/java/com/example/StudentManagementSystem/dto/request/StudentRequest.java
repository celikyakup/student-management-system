package com.example.StudentManagementSystem.dto.request;

import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    private String name;

    private String surname;

    private String studentNumber;

    private Integer classInfo;

    private LocalDate birthDate;

    private Gender gender;

    private User user;

}
