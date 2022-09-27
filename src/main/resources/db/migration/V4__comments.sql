DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    userid bigserial NOT NULL,
    postid bigserial, 
    content varchar(140) NOT NULL,
    date TIMESTAMP,
    username VARCHAR(40),
    constraint fk_comments_users foreign key(userid) references users(id),
    constraint fk_comments_post foreign key(postid) references posts(id)
    -- UNIQUE(userid, postid)
);