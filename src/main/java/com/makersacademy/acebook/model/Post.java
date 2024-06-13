package com.makersacademy.acebook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


//    No post No like, one post can have many likes
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    set ensures element uniqueness? but has problem , so changed to list
    private List<Like> likes = new ArrayList<>();


}