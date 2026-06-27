package com.fsdarvind.springSecurityRevision.service;

import com.fsdarvind.springSecurityRevision.dto.UserDTO;
import com.fsdarvind.springSecurityRevision.model.User;
import com.fsdarvind.springSecurityRevision.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserRepositoryImp {
    private final UserRepository userRepository;
    UserRepositoryImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()){
            return users.stream().map(UserDTO::fromEntity).toList();
        }
        return Collections.emptyList();
    }

    public User saveUser(User user){
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
            if(user.getPassword()!=null)
                fetchedUser.setPassword(user.getPassword());
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
}
