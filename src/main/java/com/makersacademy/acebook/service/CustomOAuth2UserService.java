/*
package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        logger.info("OAuth2 user attributes: {}", attributes);

        if (email == null) {
            logger.error("Email not found from OAuth2 provider");
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        // Save or update user information in the database
        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.info("Creating new user with email: {}", email);
            user = new User();
            user.setEmail(email);
            user.setUsername((String) attributes.get("name"));
            user.setProfilePictureUrl((String) attributes.get("picture"));
            userRepository.save(user);
            logger.info("New user created with email: {}", email);
        } else {
            logger.info("Updating existing user with email: {}", email);
            user.setUsername((String) attributes.get("name"));
            user.setProfilePictureUrl((String) attributes.get("picture"));
            userRepository.save(user);
            logger.info("Existing user updated with email: {}", email);
        }

        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), "email");
    }
}
*/

package com.makersacademy.acebook.service;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // Save or update user information in the database
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setUsername((String) attributes.get("name"));
            user.setProfilePictureUrl((String) attributes.get("picture"));
            userRepository.save(user);  // Ensure user is saved here
        }

        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), "name");
    }
}




