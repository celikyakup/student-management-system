package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.UserDto;
import com.example.StudentManagementSystem.dto.UserInfoResponse;
import com.example.StudentManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserInfoResponse>> getAll(){
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoResponse> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.getById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoResponse> update(@PathVariable Long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.update(id, userDto),HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.userService.deleteById(id);
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<UserInfoResponse> getByUsername(@PathVariable("userName") String name){
        return new ResponseEntity<>(this.userService.getByUserName(name),HttpStatus.OK );
    }
}
