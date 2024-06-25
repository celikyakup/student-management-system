package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.request.ExamRequest;
import com.example.StudentManagementSystem.dto.response.ExamResponse;
import com.example.StudentManagementSystem.entity.Exam;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper extends BaseMapper<Exam, ExamRequest, ExamResponse> {
}
