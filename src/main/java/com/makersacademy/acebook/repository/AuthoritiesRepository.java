package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Authority;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthoritiesRepository extends CrudRepository<Authority, UUID> {
}
