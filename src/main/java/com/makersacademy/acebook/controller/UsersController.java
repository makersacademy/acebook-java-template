package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/users")
    public User afterRegistration(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }
}
