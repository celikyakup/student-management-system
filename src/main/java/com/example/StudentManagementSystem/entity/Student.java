package com.example.StudentManagementSystem.entity;

import com.example.StudentManagementSystem.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true,updatable = false)
    private String studentNumber;

    private Integer classInfo;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(name = "student2lesson",
    joinColumns =@JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "lesson_id"))
    private List<Lesson> lessonList;

    @NotNull(message = "User null olamaz !!")
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade =CascadeType.REMOVE )
    @JsonIgnore
    private List<Attendance> attendanceList;

    @OneToMany(mappedBy = "student",fetch =FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Exam> examList;

}
