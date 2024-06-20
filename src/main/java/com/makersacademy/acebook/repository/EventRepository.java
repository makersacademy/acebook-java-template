package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

}
