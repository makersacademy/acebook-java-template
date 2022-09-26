package com.makersacademy.acebook.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

// Service classes allow calling methods in the html webpage (see friends.html in 'Potential-Connections')
// Have to add them as a model attribute first in the respective controller class first
@Service
public class MutualFriendsService {
  
  @Autowired
  private UserRepository userRepository;

  public Iterable<User> getMutualFriends(String userId1, String userId2) {
    Long Id1 = Long.valueOf(userId1);
    Long Id2 = Long.valueOf(userId2);
    return userRepository.getMutualFriends(Id1, Id2);
  }

  public String getRequestStatus(String userId1, String userId2) {
    Long Id1 = Long.valueOf(userId1);
    Long Id2 = Long.valueOf(userId2);
    return userRepository.getRequestStatus(Id1, Id2);
  }

  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  public String sayHello(String string) {
    return "Hello, " + string;
  }
}
