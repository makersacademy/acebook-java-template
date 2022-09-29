package com.makersacademy.acebook.services;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import com.makersacademy.acebook.repository.FriendshipRepository;

// Service classes allow calling methods in the html webpage (see friends.html in 'Potential-Connections')
// Have to add them as a model attribute first in the respective controller class first
@Service
public class FriendsService {
  
  // Can use this service to store user(friend) ids for accessing outer loop variable in inner nested loop for checking blocked relationships
  private String id;
  private int count;
  private Collection<String> mutualFriends;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FriendshipRepository friendshipRepository;

  public User findById(Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    return userOptional.get();
  }

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

  public void addAsFriends(String userId1, String userId2) {
    Long id1 = Long.valueOf(userId1);
    Long id2 = Long.valueOf(userId2);
    userRepository.addAsFriends(id1, id2);
  }

  public void deleteById(String userId1, String userId2) {
    Long id1 = Long.valueOf(userId1);
    Long id2 = Long.valueOf(userId2);
    Long idToDelete = userRepository.getFriendshipId(id1, id2);
    friendshipRepository.deleteById(idToDelete);
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

  // Temporary list that can eventually be used to store mutual friends list (might change to iterable)
  public Collection<String> getMutualFriends() {
    return this.mutualFriends;
  }

  public void addMutualFriend(String id) {
    this.mutualFriends.add(id);
  }

  public void deleteMutualFriends() {
    this.mutualFriends.clear();;
  }
}
