//package com.makersacademy.acebook.model;
//
//import javax.persistence.*;
//
//@Table(name="FRIENDS")
//@Entity
//public class Friendship {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name="person_id")
//    private User person;
//
//    @ManyToOne
//    @JoinColumn(name="friend_id")
//    private User friend;
//
//    public Friendship(User person, User friend){
//        this.person = person;
//        this.friend = friend;
//    }
//
//    public Friendship(){
//
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    @Override
//    public boolean equals(Object o){
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Friendship that = (Friendship) o;
//
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode(){
//        return (int) (id ^ (id >>> 32));
//    }
//}
