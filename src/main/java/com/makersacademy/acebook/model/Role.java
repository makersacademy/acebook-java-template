package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/* @Entity defines that a class can be mapped to a table.
This is just how JPA is designed- needs a bare minimum of
@Entity and @Id.

(Because no @Table annotation exists, it is assumed that this
entity is mapped to a table named Role)
 */
@Entity
/* This is where you create variables for your class,
generate getters & setters.
 */
public class Role {

    @Id
    private String name;

    /* @ManyToMany is defined in both entities.
    Users class is the owner.
     */
    @ManyToMany(mappedBy = "role")

    // A list of users
    private List<User> users;

    /* getters & setters act as encapsulation (the aim is to make sure
    that sensitive data is hidden from users. To achieve this you:
        - declare class variable as private;
        - create public get & set methods to access the value;
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // constructors(acts as an initializer)
    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }
}
