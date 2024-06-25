package com.example.StudentManagementSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private boolean present;

   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
   @JoinColumn(name = "student_id",referencedColumnName = "id")
   private Student student;

   @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "lesson_id",referencedColumnName = "id")
    private Lesson lesson;


}
