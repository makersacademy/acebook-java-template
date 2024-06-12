package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/userLoggedIn")
    public Map<String, Boolean> userLoggedIn() {
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", authService.isLoggedIn());
        return response;
    }
}

