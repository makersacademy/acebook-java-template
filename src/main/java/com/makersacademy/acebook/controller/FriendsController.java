package com.makersacademy.acebook.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Map;

@RestController
public class FriendsController {
  @PersistenceContext
  EntityManager entityManager;

  @Transactional
  @PostMapping("/friends")
  public void newFriendship(@RequestBody Map<String, Long> body) {
    entityManager.createNativeQuery(
            "INSERT INTO friends (person_id, friend_id, accepted) VALUES(?, ?, TRUE)")
            .setParameter(1, body.get("person_id"))
            .setParameter(2, body.get("friend_id"))
            .executeUpdate();
    entityManager.createNativeQuery(
            "INSERT INTO friends (person_id, friend_id, accepted) VALUES(?, ?, TRUE)")
            .setParameter(1, body.get("friend_id"))
            .setParameter(2, body.get("person_id"))
            .executeUpdate();
  }

  @Transactional
  @PostMapping("/accept-friends")
  public void acceptFriendship(@RequestBody Map<String, Long> body) {
    entityManager.createNativeQuery(
            "INSERT INTO friends (person_id, friend_id, accepted) VALUES(?, ?, TRUE)")
            .setParameter(1, body.get("person_id"))
            .setParameter(2, body.get("friend_id"))
            .executeUpdate();
    entityManager.createNativeQuery("UPDATE friends SET accepted = TRUE WHERE friend_id = ? AND person_id = ?")
            .setParameter(1, body.get("person_id"))
            .setParameter(2, body.get("friend_id"))
            .executeUpdate();
  }

  @Transactional
  @PostMapping("/reject-friends")
  public void rejectFriendship(@RequestBody Map<String, Long> body) {
    entityManager.createNativeQuery(
            "DELETE FROM friends WHERE person_id = ? AND friend_id = ?")
            .setParameter(1, body.get("person_id"))
            .setParameter(2, body.get("friend_id"))
            .executeUpdate();
  }

  @Transactional
  @PostMapping("/delete-friends")
  public void deleteFriendship(@RequestBody Map<String, Long> body) {
    entityManager.createNativeQuery(
            "DELETE FROM friends WHERE person_id = ? AND friend_id = ?")
            .setParameter(1, body.get("person_id"))
            .setParameter(2, body.get("friend_id"))
            .executeUpdate();
    entityManager.createNativeQuery(
            "DELETE FROM friends WHERE person_id = ? AND friend_id = ?")
            .setParameter(1, body.get("friend_id"))
            .setParameter(2, body.get("person_id"))
            .executeUpdate();
  }
}
