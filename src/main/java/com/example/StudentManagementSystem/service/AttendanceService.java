package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.request.AttendanceRequest;
import com.example.StudentManagementSystem.dto.response.AttendanceResponse;
import com.example.StudentManagementSystem.entity.Attendance;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.AttendanceMapper;
import com.example.StudentManagementSystem.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceResponse getById(Long id){
        return this.attendanceMapper.entityToResponse(this.attendanceRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li devamsızlık bulunamadı !!")));
    }

    public List<AttendanceResponse> getAll(){
        return this.attendanceMapper.entityToListResponse(this.attendanceRepository.findAll());
    }

    public AttendanceResponse save(AttendanceRequest attendanceRequest){
        Optional<Attendance> attendanceFromDb=this.attendanceRepository.findByLessonIdAndStudentId(attendanceRequest.getLesson().getId(), attendanceRequest.getStudent().getId());
        if (attendanceFromDb.isPresent()){
            throw new MethodArgumentNotValidException("Bu devamsızlık bilgisi daha önce kaydedilmiştir !!");
        }
        return this.attendanceMapper.entityToResponse(this.attendanceRepository.save(this.attendanceMapper.requestToEntity(attendanceRequest)));
    }

    public AttendanceResponse update(Long id,AttendanceRequest attendanceRequest){
        Attendance attendanceFromDb=this.attendanceRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li devamsızlık bilgisi bulunamadı !!"));
        this.attendanceMapper.update(attendanceFromDb,attendanceRequest);
        return this.attendanceMapper.entityToResponse(this.attendanceRepository.save(attendanceFromDb));
    }

    public void deleteById(Long id){
        Attendance attendanceFromDb=this.attendanceRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li devamsızlık bilgisi bulunamadı !!"));
        this.attendanceRepository.deleteById(id);
    }
}
