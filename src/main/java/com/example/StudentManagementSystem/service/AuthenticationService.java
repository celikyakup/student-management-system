package com.example.StudentManagementSystem.service;

import com.example.StudentManagementSystem.dto.UserDto;
import com.example.StudentManagementSystem.dto.UserRequest;
import com.example.StudentManagementSystem.dto.UserResponse;

import com.example.StudentManagementSystem.entity.User;
import com.example.StudentManagementSystem.enums.RoleType;
import com.example.StudentManagementSystem.exception.MethodArgumentNotValidException;
import com.example.StudentManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserResponse save(UserDto userDto){

        User user=User.builder().username(userDto.getUsername()).password(passwordEncoder.encode(userDto.getPassword())).firstName(userDto.getFirstName()).role(userDto.getRole()).build();
        Optional<User> isDbExist=this.userRepository.findByUsername(userDto.getUsername());
        if (isDbExist.isEmpty()){
            userRepository.save(user);
            var token=jwtService.generateToken(user);
            return UserResponse.builder().token(token).build();
        }
        else throw new MethodArgumentNotValidException("Bu kullanıcı adı ile daha önce kayıt yapılmıştır !!");

    }
    public UserResponse auth(UserRequest userRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(),userRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Kullanıcı adı veya şifre yanlış !!",e);
        }

        User user=userRepository.findByUsername(userRequest.getUsername()).orElseThrow(()->new UsernameNotFoundException("Kullanıcı adı veya şifre yanlış!"));
        String token= jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

}
