package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.request.TeacherRequest;
import com.example.StudentManagementSystem.dto.response.TeacherResponse;
import com.example.StudentManagementSystem.entity.Lesson;
import com.example.StudentManagementSystem.entity.Teacher;
import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.RoleType;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.TeacherMapper;
import com.example.StudentManagementSystem.repository.LessonRepository;
import com.example.StudentManagementSystem.repository.TeacherRepository;
import com.example.StudentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public TeacherResponse findById(Long id){
        return this.teacherMapper.entityToResponse(this.teacherRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li kullanıcı bulunamadı")));
    }
    public List<TeacherResponse> findAll(){
        return this.teacherMapper.entityToListResponse(this.teacherRepository.findAll());
    }

    public TeacherResponse save(TeacherRequest teacherRequest){
        Optional<Teacher> teacherFromDb=this.teacherRepository.findByIdentityNumber(teacherRequest.getIdentityNumber());
        User user=this.userRepository.findById(teacherRequest.getUser().getId()).get();

        if (teacherFromDb.isPresent()){
            throw new MethodArgumentNotValidException("Bu öğretmenin kaydı daha önce yapılmış !!");
        }else if (user.getRole()!=teacherRequest.getUser().getRole() ) {
            throw new MethodArgumentNotValidException("Kullanıcı rolü yanlış girildi !!");
        } else if (teacherRequest.getUser().getRole()!= RoleType.TEACHER) {
            throw new MethodArgumentNotValidException("Yanlış Yetkilendirme !!");
        }
        Teacher teacher=this.teacherMapper.requestToEntity(teacherRequest);
        return this.teacherMapper.entityToResponse(this.teacherRepository.save(teacher));
    }

    public TeacherResponse update(Long id,TeacherRequest teacherRequest){
        Teacher teacherFromDb=this.teacherRepository.findById(id).orElseThrow(()->new NotFoundException(id+" id'li öğretmen sistemde kayıtlı değil"));
        this.teacherMapper.update(teacherFromDb,teacherRequest);
        return this.teacherMapper.entityToResponse(this.teacherRepository.save(teacherFromDb));
    }

    public void deleteById(Long id){
        Optional<Teacher> teacherFromDb=this.teacherRepository.findById(id);
        if (teacherFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li kullanıcı bulunamadı");
        }
        this.teacherRepository.deleteById(id);
    }

    public TeacherResponse assignLessonToSTeacher(Long lessonId, Long teacherId){
        Lesson lesson=this.lessonRepository.findById(lessonId).orElseThrow(()->new NotFoundException(lessonId +" id'li ders bulunamadı !!"));
        Teacher teacher=this.teacherRepository.findById(teacherId).orElseThrow(()->new NotFoundException(teacherId+" id'li kullanıcı bulunamadı !!"));

        List<Lesson> lessons= teacher.getLessonList();
        List<Long> lessonIds=new ArrayList<>();
        for (Lesson les:lessons){
            lessonIds.add(les.getId());
        }
        if (lessonIds.contains(lessonId)){
            throw new MethodArgumentNotValidException("Bu derse daha önce kayıt olundu !!");
        }
        teacher.getLessonList().add(lesson);
        return this.teacherMapper.entityToResponse(this.teacherRepository.save(teacher));
    }

    public TeacherResponse deleteLessonToTeacher(Long lessonId,Long studentId){
        Lesson lesson=this.lessonRepository.findById(lessonId).orElseThrow(()->new NotFoundException(lessonId +" id'li ders bulunamadı !!"));
        Teacher teacher=this.teacherRepository.findById(studentId).orElseThrow(()->new NotFoundException(studentId+" id'li kullanıcı bulunamadı !!"));
        if (!teacher.getLessonList().contains(lesson)){
            throw new NotFoundException("Bu derse kayıt olunmamış veya kayıt kaldırılmış !!");
        }
        teacher.getLessonList().remove(lesson);
        return this.teacherMapper.entityToResponse(this.teacherRepository.save(teacher));
    }
}
