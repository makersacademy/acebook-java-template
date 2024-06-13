package com.makersacademy.acebook.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMMENTS_LIKES")
@NoArgsConstructor
public class LikeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public LikeComment(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }
}
