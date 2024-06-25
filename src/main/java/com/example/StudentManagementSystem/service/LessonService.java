package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.request.LessonRequest;
import com.example.StudentManagementSystem.dto.response.LessonResponse;
import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.LessonMapper;
import com.example.StudentManagementSystem.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public List<LessonResponse> findAll(){
        return this.lessonMapper.entityToListResponse(this.lessonRepository.findAll());
    }
    public LessonResponse findById(Long id){
        return this.lessonMapper.entityToResponse(this.lessonRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li kullanıcı bulunamadı")));
    }

    public LessonResponse save(LessonRequest lessonRequest){
        Optional<Lesson> lessonIsDb=this.lessonRepository.findByLessonCodeAndLessonStartDateAndLessonEndDate(lessonRequest.getLessonCode(),lessonRequest.getLessonStartDate(),lessonRequest.getLessonEndDate());
        if (lessonIsDb.isPresent()){
            throw new MethodArgumentNotValidException("Bu ders daha önce kayıt edilmiş !!");
        } else if (lessonRequest.getLessonStartDate().isAfter(lessonRequest.getLessonEndDate())) {
            throw new MethodArgumentNotValidException("Başlangıç tarihi bitiş tarihinden sonraki bir tarih olamaz !!");
        }
        return lessonMapper.entityToResponse(this.lessonRepository.save(this.lessonMapper.requestToEntity(lessonRequest)));
    }

    public LessonResponse update(Long id,LessonRequest lessonRequest){
        Optional<Lesson> studentFromDb=this.lessonRepository.findById(id);
        if (studentFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li kullanıcı bulunamadı !!");
        }else if (lessonRequest.getLessonStartDate().isAfter(lessonRequest.getLessonEndDate())) {
            throw new MethodArgumentNotValidException("Başlangıç tarihi bitiş tarihinden sonraki bir tarih olamaz !!");
        }
        this.lessonMapper.update(studentFromDb.get(),lessonRequest);
        return this.lessonMapper.entityToResponse(this.lessonRepository.save(studentFromDb.get()));
    }
    public void deleteById(Long id){
        Optional<Lesson> studentFromDb=this.lessonRepository.findById(id);
        if (studentFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li kullanıcı bulunamadı !!");
        }
        this.lessonRepository.delete(studentFromDb.get());
    }
}
