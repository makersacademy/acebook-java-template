package com.makersacademy.acebook.auth;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public Boolean isLoggedIn() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return !username.equals("anonymousUser");
    }

}
