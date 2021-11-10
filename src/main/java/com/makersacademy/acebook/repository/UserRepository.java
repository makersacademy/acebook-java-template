package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
    
  @Query(value="SELECT EXISTS(SELECT 1 FROM users WHERE username = ?1)  ", nativeQuery=true)
  boolean usernameExists(String username);

}
