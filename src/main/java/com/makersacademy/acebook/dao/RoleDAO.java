package com.makersacademy.acebook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.makersacademy.acebook.model.Role;

/* JpaRepository & CrudRepository are the same thing except Jpa
has extended functionality. Google for more info.

DAO - Data Access Object. Allows us to isolate the app/business layer
from the persistence layer (usually a relational db) using an abstract API.

It's responsible for interacting with db to perform CRUD ops in the app.
Using DAO pattern will make the db ops as a separate layer in app development.
As opposed to writing separate Java methods with tons of queries.

 */
public interface RoleDAO extends JpaRepository<Role, String> {

}

/* RoleDAO extends the JpaRepository interface. The type of entity and ID
that it works with, Role and String, are specified in the generic parameters on JpaRepository.
By extending JpaRepository, RoleDAO inherits several methods for working with Role
persistence, including methods for saving, deleting, and finding Role entities.

Spring Data JPA also lets you define other query methods by declaring their method signature.
For example, RoleDAO includes the findByLastName() method.

In a typical Java application, you might expect to write a class that implements RoleDAO.
However, that is what makes Spring Data JPA so powerful: You need not write an implementation of the
repository interface. Spring Data JPA creates an implementation when you run the application.
 */
