package com.example.StudentManagementSystem.dto;

import com.example.StudentManagementSystem.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    public Long id;
    private String username;
    private String firstName;
    private RoleType role;
}
