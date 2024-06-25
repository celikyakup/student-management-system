package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.request.TeacherRequest;
import com.example.StudentManagementSystem.dto.response.TeacherResponse;
import com.example.StudentManagementSystem.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> findAll(){
        return new ResponseEntity<>(this.teacherService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.teacherService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> save(@RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(this.teacherService.save(teacherRequest),HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id,@RequestBody TeacherRequest teacherRequest){
        return new ResponseEntity<>(this.teacherService.update(id,teacherRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.teacherService.deleteById(id);
    }

    @PutMapping("/{teacherId}/lessons/{lessonId}")
    public ResponseEntity<TeacherResponse> assignLessonToTeacher(@PathVariable("teacherId")Long teacherId,@PathVariable("lessonId")Long lessonId){
        return new ResponseEntity<>(this.teacherService.assignLessonToSTeacher(lessonId,teacherId),HttpStatus.OK );
    }

    @PutMapping("/{teacherId}/delete-lesson/{lessonId}")
    public ResponseEntity<TeacherResponse> deleteLessonToTeacher(@PathVariable("teacherId")Long teacherId,@PathVariable("lessonId")Long lessonId){
        return new ResponseEntity<>(this.teacherService.deleteLessonToTeacher(lessonId,teacherId), HttpStatus.OK);
    }
}
