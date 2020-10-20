package com.example.demo.service.impl;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @Override
    public UserDto save(UserDto user) {
        User u=this.modelMapper.map(user,User.class);
        u=this.userRepository.save(u);
        user.setId(u.getId());
        return user;
    }

    @Override
    public UserDto getById(Long id) {
        User u=this.userRepository.getOne(id);
        return this.modelMapper.map(u,UserDto.class);
    }

    @Override
    public UserDto getByUsername(String username) {
        return null;
    }

    @Transactional
    public Boolean update(String username,String psw) {

        try {
            System.out.println("1-ŞUAN VERİTABANINA GİDECEK ŞİFRE"+psw);
            System.out.println("1-VERİTABANINA GİDECEK USERNAME"+username);
            User u = this.userRepository.findByUsername(username);
            System.out.println("2-VERİTABANINDAN GELEN NESNENİN ADI"+u.getUsername());
            System.out.println("2-VERİTABANINDAN GELEN NESNENİN ŞİFRESİ"+u.getPassword());
            u.setPassword(bCryptPasswordEncoder.encode(psw));
            System.out.println("3-DEĞİŞTİKTEN SONRAKİ ADI:"+u.getUsername());
            System.out.println("3-DEĞİŞTİKTEN SONRAKİ ŞİFRE:"+u.getPassword());
            u= this.userRepository.save(u);
            return  Boolean.TRUE;
        }catch (Exception e) {
            log.error("Change Operation =>",e);
            return Boolean.FALSE;
        }


    }

    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }
}
