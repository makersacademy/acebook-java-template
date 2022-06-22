package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthoritiesRepository extends CrudRepository<Authority, Long> {
}
