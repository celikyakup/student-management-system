package com.example.StudentManagementSystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {

    private Long id;

    private String lessonName;

    private String lessonCode;

    private String lessonSubject;

    private LocalDateTime lessonStartDate;

    private LocalDateTime lessonEndDate;
}
