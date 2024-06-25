package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.request.StudentRequest;
import com.example.StudentManagementSystem.dto.response.StudentResponse;
import com.example.StudentManagementSystem.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAll(){
        return new ResponseEntity<>(this.studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.studentService.getById(id),HttpStatus.OK );
    }
    @PostMapping
    public ResponseEntity<StudentResponse> save(@RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(this.studentService.save(studentRequest),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> update(@PathVariable Long id,@RequestBody StudentRequest studentRequest){
        return new ResponseEntity<>(this.studentService.update(id,studentRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.studentService.deleteById(id);
    }

    @PutMapping("/{studentsId}/lessons/{lessonsId}")
    public ResponseEntity<StudentResponse> assignLessonToStudent(@PathVariable("studentsId")Long studentId,@PathVariable("lessonsId")Long lessonId) {
        return new ResponseEntity<>(this.studentService.assignLessonToStudent(lessonId,studentId),HttpStatus.OK );
    }

    @PutMapping("/{studentsId}/delete-lessons/{lessonsId}")
    public ResponseEntity<StudentResponse> deleteLessonToStudent(@PathVariable("studentsId")Long studentId,@PathVariable("lessonsId")Long lessonId){
        return new ResponseEntity<>(this.studentService.deleteLessonToStudent(lessonId,studentId),HttpStatus.OK );
    }
}
