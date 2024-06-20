package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Attendee;
import org.springframework.data.repository.CrudRepository;

public interface AttendeesRepository extends CrudRepository<Attendee, Long> {
}
