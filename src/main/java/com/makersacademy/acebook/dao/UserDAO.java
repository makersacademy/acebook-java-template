package com.makersacademy.acebook.dao;

import com.makersacademy.acebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/* JpaRepository & CrudRepository are the same thing except Jpa
has extended functionality. Google for more info.

DAO - Data Access Object. Allows us to isolate the app/business layer
from the persistence layer (usually a relational db) using an abstract API.

It's responsible for interacting with db to perform CRUD ops in the app.
Using DAO pattern will make the db ops as a separate layer in app development.
As opposed to writing separate Java methods with tons of queries.


CMD + click opens up all the methods added by JpaRepository
 */
public interface UserDAO extends JpaRepository<User, Long> {

    User findByEmail(String email);

}

/* UserDAO extends the JpaRepository interface. The type of entity and ID
that it works with, Users and String, are specified in the generic parameters on JpaRepository.
By extending JpaRepository, UserDAO inherits several methods for working with Users
persistence, including methods for saving, deleting, and finding User entities.

Spring Data JPA also lets you define other query methods by declaring their method signature.
For example, UserDAO includes the findByEmail() method.

In a typical Java application, you might expect to write a class that implements UserDAO.
However, that is what makes Spring Data JPA so powerful: You need not write an implementation of the
repository interface. Spring Data JPA creates an implementation when you run the application.
 */
