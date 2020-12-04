package com.makersacademy.acebook.services;

import com.makersacademy.acebook.dao.UserDAO;
import com.makersacademy.acebook.model.Role;
import com.makersacademy.acebook.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    /* @Autowired references the preferred class and eliminates the need
    of creating getters & setters.

    In the below, Spring injects UserDAO property when User Service is created.
     */
    @Autowired
    private UserDAO userRepository;

    public User createUser(User user) {
        // encrypts the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        // assigns MEMBER role to the user
        Role userRole = new Role("MEMBER");
        /* ArrayList rule: if you want to add or remove elements to/from an array,
        you have to create a new one.
        */
        List<Role> roles = new ArrayList<>();
        // adds a new role into the Role list
        roles.add(userRole);
        user.setRole(roles);
        // adds user to the users db table
        userRepository.save(user);
        return user;
    }
}
