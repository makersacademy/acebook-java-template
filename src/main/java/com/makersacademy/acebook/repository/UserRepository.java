package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);

    User findByUsername(String username);

    // check if user has recieved a friend request
    // @Query(value = "select exists(SELECT * FROM friends where
    // friends.request_from = ?1)", nativeQuery = true)
    // Boolean requestStatus(Long userid);

    // requests sent to me by other users
    @Query(value = "SELECT users.username FROM users INNER JOIN friends ON users.id = friends.request_from WHERE friends.request_from as = ANY(ARRAY[?1]) AND friends.request_to = ?2", nativeQuery = true)
    Iterable<Object[]> requestsSentByOtherUsers(List<BigInteger> ids, Long userid);

    @Query(value = "SELECT users.id, users.username, users.password, users.enabled, users.image FROM users INNER JOIN friends ON users.id = friends.request_from WHERE friends.status_code = TRUE AND friends.request_to = ?1 UNION SELECT users.id, users.username, users.password, users.enabled, users.image FROM users INNER JOIN friends ON users.id = friends.request_to WHERE friends.status_code = TRUE AND friends.request_from = ?1", nativeQuery = true)
    List<User> findFriends(Long userid);
}
