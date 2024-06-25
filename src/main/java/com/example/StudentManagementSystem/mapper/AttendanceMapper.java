package com.example.StudentManagementSystem.mapper;

import com.example.StudentManagementSystem.core.BaseMapper;
import com.example.StudentManagementSystem.dto.request.AttendanceRequest;
import com.example.StudentManagementSystem.dto.response.AttendanceResponse;
import com.example.StudentManagementSystem.entity.Attendance;
import org.mapstruct.Mapper;

@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance, AttendanceRequest, AttendanceResponse> {
}
