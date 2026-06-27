package com.fsdarvind.springSecurityRevision.controllers;

import com.fsdarvind.springSecurityRevision.dto.UserDTO;
import com.fsdarvind.springSecurityRevision.model.User;
import com.fsdarvind.springSecurityRevision.service.UserRepositoryImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserAdminController {
    private final UserRepositoryImp userRepositoryImp;
    UserAdminController(UserRepositoryImp userRepositoryImp){
        this.userRepositoryImp=userRepositoryImp;
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepositoryImp.getUsers());
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user){
        UserDTO savedUser = UserDTO.fromEntity(userRepositoryImp.saveUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
        User user = userRepositoryImp.getUser(id);
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(UserDTO.fromEntity(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User toBeUpdated ){
        User user = userRepositoryImp.updateUser(id,toBeUpdated);
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(UserDTO.fromEntity(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id){
        User user = userRepositoryImp.deleteUser(id);
        if(user!=null){
            return ResponseEntity.status(HttpStatus.OK).body(UserDTO.fromEntity(user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
