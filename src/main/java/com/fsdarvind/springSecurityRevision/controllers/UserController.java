package com.fsdarvind.springSecurityRevision.controllers;

import com.fsdarvind.springSecurityRevision.dto.UserDTO;
import com.fsdarvind.springSecurityRevision.model.User;
import com.fsdarvind.springSecurityRevision.service.UserRepositoryImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepositoryImp userRepositoryImp;
    public UserController(UserRepositoryImp userRepositoryImp){
        this.userRepositoryImp=userRepositoryImp;
    }
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String getUser(){
        return "Welcome!!!";
    }
}
