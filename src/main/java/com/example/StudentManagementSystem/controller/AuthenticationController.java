package com.example.StudentManagementSystem.controller;

import com.example.StudentManagementSystem.dto.UserDto;
import com.example.StudentManagementSystem.dto.UserRequest;
import com.example.StudentManagementSystem.dto.UserResponse;
import com.example.StudentManagementSystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest) throws Exception {
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }
}
