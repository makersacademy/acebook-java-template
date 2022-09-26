DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
    id bigserial PRIMARY KEY,
    userid bigserial NOT NULL,
    postid bigserial NOT NULL,
    constraint fk_likes_users foreign key(userid) references users(id),
    constraint fk_likes_posts foreign key(postid) references posts(id),
    UNIQUE(userid, postid)
);