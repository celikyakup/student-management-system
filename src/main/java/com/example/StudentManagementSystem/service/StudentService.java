package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.request.StudentRequest;
import com.example.StudentManagementSystem.dto.response.StudentResponse;
import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.Student;
import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.RoleType;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.StudentMapper;
import com.example.StudentManagementSystem.repository.LessonRepository;
import com.example.StudentManagementSystem.repository.StudentRepository;

import com.example.StudentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public List<StudentResponse> getAll(){
        return this.studentMapper.entityToListResponse(this.studentRepository.findAll());
    }

    public StudentResponse getById(Long id){
        return this.studentMapper.entityToResponse(this.studentRepository.findById(id).orElseThrow(()->new NotFoundException(id+" 'li kullanıcı bulunamadı")));
    }

    public StudentResponse save(StudentRequest studentRequest){
        Optional<Student> studentIsDb=this.studentRepository.findByStudentNumber(studentRequest.getStudentNumber());
        User user=this.userRepository.findById(studentRequest.getUser().getId()).get();
        Optional<User> userIsDb=this.userRepository.findById(studentRequest.getUser().getId());
        if (studentIsDb.isPresent()) {
            throw new MethodArgumentNotValidException("Bu öğrencinin kaydı daha önce yapılmıştır !!");
        } else if (userIsDb.isEmpty()) {
            throw new NotFoundException("Kayıtlı kullanıcı bulunamadı !!");
        }else if (user.getRole()!=studentRequest.getUser().getRole() ) {
            throw new MethodArgumentNotValidException("Kullanıcı rolü yanlış girildi !!");
        } else if (studentRequest.getUser().getRole()!= RoleType.STUDENT) {
            throw new MethodArgumentNotValidException("Yanlış Yetkilendirme !!");
        }
        Student student=this.studentMapper.requestToEntity(studentRequest);
        return this.studentMapper.entityToResponse(this.studentRepository.save(student));
    }

    public StudentResponse update(Long id,StudentRequest studentRequest){
        Student studentFromDb=this.studentRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li kullanıcı bulunamadı !!"));
        this.studentMapper.update(studentFromDb,studentRequest);
        return this.studentMapper.entityToResponse(this.studentRepository.save(studentFromDb));
    }

    public void deleteById(Long id){
        Optional<Student> studentFromDb=this.studentRepository.findById(id);
        if (studentFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li kullanıcı bulunamadı");
        }
        this.studentRepository.delete(studentFromDb.get());
    }

    public StudentResponse assignLessonToStudent(Long lessonId,Long studentId){
        Lesson lesson=this.lessonRepository.findById(lessonId).orElseThrow(()->new NotFoundException(lessonId +" id'li ders bulunamadı !!"));
        Student student=this.studentRepository.findById(studentId).orElseThrow(()->new NotFoundException(studentId+" id'li kullanıcı bulunamadı !!"));

        List<Lesson> lessons= student.getLessonList();
        List<Long> lessonIds=new ArrayList<>();
        for (Lesson les:lessons){
            lessonIds.add(les.getId());
        }
        if (lessonIds.contains(lessonId)){
            throw new MethodArgumentNotValidException("Bu derse daha önce kayıt olundu !!");
        }
        student.getLessonList().add(lesson);
        return this.studentMapper.entityToResponse(this.studentRepository.save(student));
    }

    public StudentResponse deleteLessonToStudent(Long lessonId,Long studentId){
        Lesson lesson=this.lessonRepository.findById(lessonId).orElseThrow(()->new NotFoundException(lessonId +" id'li ders bulunamadı !!"));
        Student student=this.studentRepository.findById(studentId).orElseThrow(()->new NotFoundException(studentId+" id'li kullanıcı bulunamadı !!"));
        if (!student.getLessonList().contains(lesson)){
            throw new NotFoundException("Bu derse kayıt olunmamış veya kayıt kaldırılmış !!");
        }
        student.getLessonList().remove(lesson);
        return this.studentMapper.entityToResponse(this.studentRepository.save(student));
    }

}
