package com.example.demo.api;

import com.example.demo.dto.UserDto;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiPaths.UserCTRL.CTRL)
@Api(value = ApiPaths.UserCTRL.CTRL,description = "User APIs")
@Slf4j
public class UserController {
    private final UserServiceImpl userServiceImpl;
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl=userServiceImpl;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id") Long id){
        log.info("UserController -> GetByID");
        log.debug("UserController -> GetByID -> PARAM:"+id);
        UserDto user=userServiceImpl.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation",response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        return ResponseEntity.ok(this.userServiceImpl.save(user));
    }
    



}
