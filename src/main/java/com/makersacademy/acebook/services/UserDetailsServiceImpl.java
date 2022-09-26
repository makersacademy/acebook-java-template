// package com.makersacademy.acebook.services;

// import java.util.Collection;

// import com.makersacademy.acebook.model.User;
// import com.makersacademy.acebook.repository.UserRepository;
// import com.makersacademy.acebook.repository.AuthoritiesRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// @Service
// public class UserDetailsServiceImpl implements UserDetails {

//   private final UserRepository userRepository;
  
//   @Autowired
//   public UserDetailsServiceImpl(UserRepository userRepository) {
//     this.userRepository = userRepository;
//   }

//   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     User user = userRepository.findByUserName(username);
//     if(user == null) {
//       throw new UsernameNotFoundException("Could not find user " + username);
//     }
//     return new UserRepositoryUserDetails(user);
//   }

//   private final static class UserRepositoryUserDetails extends User implements UserDetails {

//     private final AuthoritiesRepository authoritiesRepository;

//     private UserRepositoryUserDetails(User user) {
//       super();
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//       return AuthorityUtils.createAuthorityList("ROLE_USER");
//       // return null;
//     }

//     @Override
//     public String getPassword() {
//       // TODO Auto-generated method stub
//       return null;
//     }

//     @Override
//     public String getUsername() {
//       // TODO Auto-generated method stub
//       return null;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//       // TODO Auto-generated method stub
//       return false;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//       // TODO Auto-generated method stub
//       return false;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//       // TODO Auto-generated method stub
//       return false;
//     }

//     @Override
//     public boolean isEnabled() {
//       // TODO Auto-generated method stub
//       return false;
//     }
//   }
// }
