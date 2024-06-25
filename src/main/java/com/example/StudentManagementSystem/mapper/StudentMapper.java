package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.request.StudentRequest;
import com.example.StudentManagementSystem.dto.response.StudentResponse;
import com.example.StudentManagementSystem.entity.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student, StudentRequest, StudentResponse> {
}
