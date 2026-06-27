package com.fsdarvind.springSecurityRevision.dto;

import com.fsdarvind.springSecurityRevision.model.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String role;

    // creating this method to hide secret filed
    public static UserDTO fromEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
