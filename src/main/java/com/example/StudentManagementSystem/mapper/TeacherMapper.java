package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.request.TeacherRequest;
import com.example.StudentManagementSystem.dto.response.TeacherResponse;
import com.example.StudentManagementSystem.entity.Teacher;

import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher, TeacherRequest, TeacherResponse> {
}
