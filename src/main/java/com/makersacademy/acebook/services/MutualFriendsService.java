package com.makersacademy.acebook.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;

// Service classes allow calling methods in the html webpage (see friends.html in 'Potential-Connections')
// Have to add them as a model attribute first in the respective controller class first
@Service
public class MutualFriendsService {
  
  // Can use this service to store user(friend) ids for accessing outer loop variable in inner nested loop for checking blocked relationships
  private String id;
  private int count;

  @Autowired
  private UserRepository userRepository;

  public Iterable<User> getMutualFriends(String userId1, String userId2) {
    Long id1 = Long.valueOf(userId1);
    Long id2 = Long.valueOf(userId2);
    return userRepository.getMutualFriends(id1, id2);
  }

  public String getRequestStatus(String userId1, String userId2) {
    Long id1 = Long.valueOf(userId1);
    Long id2 = Long.valueOf(userId2);
    return userRepository.getRequestStatus(id1, id2);
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCount() {
    return this.count;
  }

  public void resetCount() {
    this.count = 0;
  }

  public void incrementCount() {
    this.count++;
  }

  public Iterable<User> findAll() {
    return userRepository.findAll();
  }

  public String sayHello(String string) {
    return "Hello, " + string;
  }
}
