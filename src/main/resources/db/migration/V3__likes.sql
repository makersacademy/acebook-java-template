DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
    id bigserial PRIMARY KEY,
    user_id bigserial NOT NULL,
    post_id bigserial NOT NULL,
    constraint fk_likes_users foreign key(user_id) references users(id),
    constraint fk_likes_posts foreign key(post_id) references posts(id)
);