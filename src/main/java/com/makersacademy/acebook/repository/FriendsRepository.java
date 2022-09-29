package com.makersacademy.acebook.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;

public interface FriendsRepository extends CrudRepository<Friend, Long> {

 @Query(value = "SELECT users.id FROM users INNER JOIN friends ON users.id = friends.request_to WHERE request_from = ?1", nativeQuery = true)
 Iterable<BigInteger> pendingFriends(Long request_from);

 // @Query(value = "SELECT users.username, likes.formatted_date,
 // likes.formatted_time FROM users INNER JOIN likes ON users.id = likes.userid
 // WHERE likes.postid = ?1", nativeQuery = true)

 @Query(value = "SELECT friends.id request_id, users.username FROM users INNER JOIN friends ON users.id = friends.request_from WHERE friends.request_to = ?1 AND friends.status_code = FALSE", nativeQuery = true)
 Iterable<Object[]> pendingFriendsTo(Long request_to);

 @Query(value = "SELECT users.username FROM users INNER JOIN friends ON users.id = friends.request_from WHERE friends.status_code = TRUE AND friends.request_to = ?1 UNION SELECT users.username FROM users INNER JOIN friends ON users.id = friends.request_to WHERE friends.status_code = TRUE AND friends.request_from = ?1", nativeQuery = true)
 Iterable<Object[]> findFriends(Long userid);

 void deleteById(Long requestId);
}
