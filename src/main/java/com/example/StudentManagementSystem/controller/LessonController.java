package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.request.LessonRequest;
import com.example.StudentManagementSystem.dto.response.LessonResponse;
import com.example.StudentManagementSystem.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lessons")
public class LessonController {
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonResponse>> findAll(){
       return new ResponseEntity<>(this.lessonService.findAll(),HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.lessonService.findById(id),HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<LessonResponse> save(@RequestBody LessonRequest lessonRequest){
        return new ResponseEntity<>(this.lessonService.save(lessonRequest),HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> update(@PathVariable Long id,@RequestBody LessonRequest lessonRequest){
        return new ResponseEntity<>(this.lessonService.update(id, lessonRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        this.lessonService.deleteById(id);
    }
}
