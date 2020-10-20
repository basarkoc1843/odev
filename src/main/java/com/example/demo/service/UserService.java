package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;

public interface UserService {
    UserDto save(UserDto user);

    UserDto getById(Long id);

    UserDto getByUsername(String username);

}
