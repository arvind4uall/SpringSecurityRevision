package com.fsdarvind.springSecurityRevision.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

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
    private String email;
    @NotBlank
    @Size(min = 5, max = 10, message = "Please enter password of length 5-10!!")
    private String password;
    @NotBlank(message = "Role can not be blank!!")
    private String role;
}
