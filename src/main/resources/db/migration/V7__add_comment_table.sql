CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    username varchar(50) NOT NULL,
    post_id bigserial, 
    content varchar(100) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    constraint fk_comments_post foreign key(post_id) references posts(id)
);