DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    post_id bigserial, 
    content varchar(140) NOT NULL,
    created_at TIMESTAMP,
    constraint fk_comments_post foreign key(post_id) references posts(id)
);