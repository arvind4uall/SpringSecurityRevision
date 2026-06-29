package com.fsdarvind.springSecurityRevision.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="app_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name can not be null!!")
    private String name;

    @Email(message = "Invalid email format!!")
    @NotBlank(message = "Email can not be blank!!")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password can not be blank!!")
    @Size(min = 5,  message = "Please enter password of minimum length 5!!")
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Role can not be blank!!")
    @Column(nullable = false)
    private String role;
}
