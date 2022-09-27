DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    postid bigserial, 
    content varchar(140) NOT NULL,
    date TIMESTAMP,
    constraint fk_comments_post foreign key(postid) references posts(id)
);