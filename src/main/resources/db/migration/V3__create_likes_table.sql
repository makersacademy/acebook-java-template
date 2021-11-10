DROP TABLE IF EXISTS likes;
CREATE TABLE likes (

user_id bigint NOT NULL, 
post_id bigint NOT NULL,
like_id bigserial NOT NULL,
is_like boolean NOT NULL,
like_count bigint,
dislike_count bigint,
constraint fk_user_id foreign key(user_id) references users(id),
constraint fk_post_id foreign key(post_id) references posts(id)

);
