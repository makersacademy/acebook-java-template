CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  post_id bigint,
  username varchar(50) NOT NULL,
  constraint fk_likes_users foreign key(username) references users(username),
  constraint fk_likes_posts foreign key(post_id) references posts(id)
);
