DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    username varchar(50) NOT NULL,
    post_id bigserial NOT NULL,
    comment_content varchar(150) NOT NULL,
    constraint fk_comments_users foreign key(username) references users(username),
    constraint fk_comments_posts foreign key(post_id) references posts(id)
);
