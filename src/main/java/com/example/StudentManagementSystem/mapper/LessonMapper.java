package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.request.LessonRequest;
import com.example.StudentManagementSystem.dto.response.LessonResponse;
import com.example.StudentManagementSystem.entity.Lesson;
import org.mapstruct.Mapper;


@Mapper
public interface LessonMapper extends BaseMapper<Lesson, LessonRequest, LessonResponse> {
}
