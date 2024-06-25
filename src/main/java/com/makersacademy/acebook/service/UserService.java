package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        validateUser(user, false);
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolationException(e);
        }
    }

    public void update(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Log details for debugging
        logger.debug("Existing user details: {}", existingUser);
        logger.debug("Input user details: {}", user);

        // Handle empty inputs
        if (user.getUsername() != null && user.getUsername().isEmpty()) {
            user.setUsername(null);
        }
        if (user.getEmail() != null && user.getEmail().isEmpty()) {
            user.setEmail(null);
        }

        // Validate user before applying changes
        validateUser(user, true);

        // Update username if changed and valid
        if (user.getUsername() != null && !user.getUsername().equals(existingUser.getUsername())) {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new IllegalArgumentException("Username already exists");
            }
            existingUser.setUsername(user.getUsername());
        }

        // Update email if changed and valid
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new IllegalArgumentException("Email already exists");
            }
            existingUser.setEmail(user.getEmail());
        }

        // Update other modifiable fields
        if (user.getLanguage() != null) {
            existingUser.setLanguage(user.getLanguage());
        }
        if (user.getCity() != null) {
            existingUser.setCity(user.getCity());
        }

        // Save the updated user back to the repository
        try {
            userRepository.save(existingUser);
        } catch (DataIntegrityViolationException e) {
            handleDataIntegrityViolationException(e);
        }
    }

    public void changePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword() != null ? user.getPassword() : "") // Handle null password
                .roles("USER")
                .build();
    }

    private void validateUser(User user, boolean isUpdate) {
        // Validate username
        if (user.getUsername() != null && user.getUsername().length() > 50) {
            throw new IllegalArgumentException("Username exceeds maximum allowed length of 50 characters");
        }
    }

    private void handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        Throwable rootCause = getRootCause(e);
        if (rootCause.getMessage().contains("users_username_key")) {
            throw new IllegalArgumentException("Username already exists");
        } else if (rootCause.getMessage().contains("users_email_key")) {
            throw new IllegalArgumentException("Email already exists. Please choose another one.");
        } else {
            throw e;
        }
    }

    private Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause != null) {
            return getRootCause(cause);
        } else {
            return throwable;
        }
    }
}
