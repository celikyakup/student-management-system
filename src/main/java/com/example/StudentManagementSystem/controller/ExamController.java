package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.request.ExamRequest;
import com.example.StudentManagementSystem.dto.response.ExamResponse;
import com.example.StudentManagementSystem.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamController {
    private final ExamService examService;

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.examService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExamResponse>> getAll(){
        return new ResponseEntity<>(this.examService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamResponse> save(@RequestBody ExamRequest examRequest){
        return new ResponseEntity<>(this.examService.save(examRequest),HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponse> update(@PathVariable Long id,@RequestBody ExamRequest examRequest){
        return new ResponseEntity<>(this.examService.update(id, examRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.examService.delete(id);
    }
}
