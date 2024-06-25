package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.request.AttendanceRequest;
import com.example.StudentManagementSystem.dto.response.AttendanceResponse;
import com.example.StudentManagementSystem.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping
    public ResponseEntity<List<AttendanceResponse>> findAll(){
        return new ResponseEntity<>(this.attendanceService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.attendanceService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AttendanceResponse> save(@RequestBody AttendanceRequest attendanceRequest){
        return new ResponseEntity<>(this.attendanceService.save(attendanceRequest),HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttendanceResponse> update(@PathVariable Long id,@RequestBody AttendanceRequest attendanceRequest){
        return new ResponseEntity<>(this.attendanceService.update(id,attendanceRequest),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.attendanceService.deleteById(id);
    }
}
