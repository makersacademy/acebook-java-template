package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.Param;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  @Query(value = "SELECT EXISTS(SELECT 1 FROM users WHERE username = ?1)  ", nativeQuery = true)
  boolean usernameExists(String username);

  public User findByUsername(String username);

}
