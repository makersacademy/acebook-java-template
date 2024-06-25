package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findIdByUsername(String name);
    User findByEmail(String email);
    List<User> findByUsernameContaining(String username);

}
