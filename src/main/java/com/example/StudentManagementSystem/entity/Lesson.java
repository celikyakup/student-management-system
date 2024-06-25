package com.example.StudentManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lessons")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lessonName;

    private String lessonCode;

    private String lessonSubject;

    private LocalDateTime lessonStartDate;

    private LocalDateTime lessonEndDate;

    @ManyToMany(mappedBy = "lessonList",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Student> studentList;

    @ManyToMany(mappedBy = "lessonList",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Teacher> teacherList;

    @OneToMany(mappedBy = "lesson",fetch = FetchType.LAZY,cascade =CascadeType.REMOVE )
    @JsonIgnore
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "lesson",fetch =FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Exam> examList;
}
