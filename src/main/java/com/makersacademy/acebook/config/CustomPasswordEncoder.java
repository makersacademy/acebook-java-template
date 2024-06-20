package com.makersacademy.acebook.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    private final PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
    private final PasswordEncoder noopEncoder = NoOpPasswordEncoder.getInstance();

    @Override
    public String encode(CharSequence rawPassword) {
        return bcryptEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword.startsWith("{bcrypt}")) {
            return bcryptEncoder.matches(rawPassword, encodedPassword.substring(8));
        } else {
            return noopEncoder.matches(rawPassword, encodedPassword);
        }
    }
}
