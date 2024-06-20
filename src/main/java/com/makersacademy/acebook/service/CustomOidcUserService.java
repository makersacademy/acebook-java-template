//package com.makersacademy.acebook.service;
//
//import com.makersacademy.acebook.model.User;
//import com.makersacademy.acebook.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
//import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class CustomOidcUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        String username;
//
//        if (oAuth2User instanceof OidcUser) {
//            OidcUser oidcUser = (OidcUser) oAuth2User;
//            username = oidcUser.getFullName();  // Assuming full name is used as username
//
//            // Save user information to the database
//            saveUser(username);
//
//            return new DefaultOidcUser(mappedAuthorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
//        } else {
//            username = oAuth2User.getAttribute("name");
//
//            // Save user information to the database
//            saveUser(username);
//
//            return new DefaultOAuth2User(mappedAuthorities, oAuth2User.getAttributes(), "name");
//        }
//    }
//
//    private void saveUser(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            user = new User();
//            user.setUsername(username);
//            userRepository.save(user);
//        }
//    }
//}
