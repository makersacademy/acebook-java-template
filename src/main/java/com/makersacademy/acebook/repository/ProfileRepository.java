package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
