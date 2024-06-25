package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.UserDto;
import com.example.StudentManagementSystem.dto.UserInfoResponse;
import com.example.StudentManagementSystem.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User, UserDto, UserInfoResponse> {
}