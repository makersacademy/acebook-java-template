package com.makersacademy.acebook.repository;
import org.springframework.data.domain.Sort;
import com.makersacademy.acebook.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends JpaRepository<Post, Long> {

}
