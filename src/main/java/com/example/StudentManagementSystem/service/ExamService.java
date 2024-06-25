package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.request.ExamRequest;
import com.example.StudentManagementSystem.dto.response.ExamResponse;
import com.example.StudentManagementSystem.entity.Exam;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.ExamMapper;
import com.example.StudentManagementSystem.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public List<ExamResponse> getAll(){
        return this.examMapper.entityToListResponse(this.examRepository.findAll());
    }

    public ExamResponse getById(Long id){
        return this.examMapper.entityToResponse(this.examRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li sınav bulunamadı !!")));
    }

    public ExamResponse save(ExamRequest examRequest){
        Optional<Exam> examFromDb=this.examRepository.findByStudentIdAndLessonId(examRequest.getStudent().getId(), examRequest.getLesson().getId());
        if (examFromDb.isPresent()){
            throw new MethodArgumentNotValidException("Bu sınav daha önce kayıt edilmiş !!");
        }
        return this.examMapper.entityToResponse(this.examRepository.save(this.examMapper.requestToEntity(examRequest)));
    }

    public ExamResponse update(Long id,ExamRequest examRequest){
        Exam examIsDb=this.examRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li kullanıcı bulunamadı !!"));
        this.examMapper.update(examIsDb,examRequest);
        return this.examMapper.entityToResponse(this.examRepository.save(examIsDb));
    }

    public void delete(Long id){
        Exam examIsDb=this.examRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li kullanıcı bulunamadı !!"));
        this.examRepository.delete(examIsDb);
    }
}
