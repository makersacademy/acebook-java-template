package com.makersacademy.acebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* @Entity defines that a class can be mapped to a table.
This is just how JPA is designed- needs a bare minimum of
@Entity and @Id.

(Because no @Table annotation exists, it is assumed that this
entity is mapped to a table named Users)
 */

//values reference model properties, not tables
@JsonIgnoreProperties(value={"posts", "role", "friendOf", "friends"})
@Entity
@Table(name = "USERS")
/* This is where you create variables for your class,
generate getters & setters.
 */
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // checks for invalid email
    @Email
    // validates that the field is not empty
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    /* @ManyToMany is defined in both entities but ONLY
    one entity can own the relationship! Here, Users class is the owner
    of the relationship.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    /* @JoinTable defines the join table between two entities on the
    owner's side of the relationship. We are using this to define USER_ROLES
    table. If the @JoinTable is left out, the default values of the annotation elements
    apply. The name of the join table is supposed to be the table names of the
    associated primary tables concat'ed together using an underscore.
     */
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email")}, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })

    // A list of roles
    private List<Role> role;

    @ManyToMany
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="person_id"),
            inverseJoinColumns=@JoinColumn(name="friend_id")
    )
    private List<User> friends;

    @ManyToMany
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="friend_id"),
            inverseJoinColumns=@JoinColumn(name="person_id")
    )
    private List<User> friendOf;

    /* getters & setters act as encapsulation (the aim is to make sure
    that sensitive data is hidden from users. To achieve this you:
        - declare class variable as private;
        - create public get & set methods to access the value;
     */

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List<User> friendOf) {
        this.friendOf = friendOf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    /* constructors(acts as an initializer).
    Creates an instance of a user to be saved into a db.
     */
    public User(String email, String firstName, String lastName, String password ) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    // somehow interacts with line 37 in UserController?
    //The default constructor exists only for the sake of JPA.
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
