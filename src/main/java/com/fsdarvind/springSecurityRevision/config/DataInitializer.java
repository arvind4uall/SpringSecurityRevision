package com.fsdarvind.springSecurityRevision.config;

import com.fsdarvind.springSecurityRevision.model.User;
import com.fsdarvind.springSecurityRevision.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin User");
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");

            userRepository.save(admin);
        }

        if (userRepository.findByEmail("user@gmail.com").isEmpty()) {
            User normalUser = new User();
            normalUser.setName("Normal User");
            normalUser.setEmail("user@gmail.com");
            normalUser.setPassword(passwordEncoder.encode("user123"));
            normalUser.setRole("ROLE_USER");

            userRepository.save(normalUser);
        }
    }
}