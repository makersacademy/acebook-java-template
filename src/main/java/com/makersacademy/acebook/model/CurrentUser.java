package com.makersacademy.acebook.model;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CurrentUser {
    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
    }
}
