package com.fsdarvind.springSecurityRevision.service;

import com.fsdarvind.springSecurityRevision.dto.UserDTO;
import com.fsdarvind.springSecurityRevision.model.User;
import com.fsdarvind.springSecurityRevision.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserRepositoryImp {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    UserRepositoryImp(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()){
            return users.stream().map(UserDTO::fromEntity).toList();
        }
        return Collections.emptyList();
    }

    public User saveUser(User user){
        user.setRole(normalizeRole(user.getRole()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User user){
        User fetchedUser = userRepository.findById(id).orElse(null);
        if(fetchedUser!=null){
            if(user.getName()!=null)
                fetchedUser.setName(user.getName());
            if(user.getEmail()!=null)
                fetchedUser.setEmail(user.getEmail());
            if(user.getPassword()!=null) {
                String encodedPassword = passwordEncoder.encode(user.getPassword());
                fetchedUser.setPassword(encodedPassword);
            }
            if(user.getRole()!=null)
                fetchedUser.setRole(user.getRole());
            return userRepository.save(fetchedUser);
        }
        return null;
    }


    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            userRepository.deleteById(id);
            return user;
        }
        return null;

    }

    private String normalizeRole(String role) {

        if (role == null || role.isBlank()) {
            return "ROLE_USER";
        }

        String normalizedRole = role.trim().toUpperCase();

        return switch (normalizedRole) {
            case "ADMIN", "ROLE_ADMIN" -> "ROLE_ADMIN";
            case "USER", "ROLE", "ROLE_USER" -> "ROLE_USER";
            default -> throw new IllegalArgumentException(
                    "Invalid role: " + role + ". Allowed values: ROLE_ADMIN, ROLE_USER"
            );
        };
    }
}
