package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.UserDto;
import com.example.StudentManagementSystem.dto.UserInfoResponse;
import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.exception.NotFoundException;
import com.example.StudentManagementSystem.mapper.UserMapper;
import com.example.StudentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserInfoResponse getById(Long id){
       return this.userMapper.entityToResponse(this.userRepository.findById(id).orElseThrow(()->new NotFoundException(id+"id'li kullanıcı bulunamadı !!")));
    }
    public List<UserInfoResponse> getAll(){
        return this.userMapper.entityToListResponse(this.userRepository.findAll());
    }

    public UserInfoResponse update(Long id, UserDto userDto){
        User userFromDb=this.userRepository.findById(id).orElseThrow(()->new NotFoundException(id+ " id'li kullanıcı bulunamadı !!"));
        this.userMapper.update(userFromDb,userDto);
        return this.userMapper.entityToResponse(this.userRepository.save(userFromDb));
    }
    public void deleteById(Long id){
        Optional<User> userFromDb=this.userRepository.findById(id);

        if (userFromDb.isEmpty()){
            throw new NotFoundException(id+" id'li kullanıcı bulunamadı");
        }
        this.userRepository.delete(userFromDb.get());
    }

    public UserInfoResponse getByUserName(String name){
        return this.userMapper.entityToResponse(this.userRepository.findByUsername(name).orElseThrow(()->new NotFoundException(name+" isimli kullanıcı bulunamadı !!")));
    }
}
