
CREATE TABLE liked_posts(

user_id bigint NOT NULL references users (id), 
post_id bigint NOT NULL references posts (id),
PRIMARY KEY(user_id, post_id)

);