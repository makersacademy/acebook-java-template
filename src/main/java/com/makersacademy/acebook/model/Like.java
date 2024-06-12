package com.makersacademy.acebook.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "LIKES")
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public Like(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
