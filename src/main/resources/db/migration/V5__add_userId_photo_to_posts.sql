ALTER TABLE posts
  ADD COLUMN user_id bigint NOT NULL,
  ADD COLUMN photo varchar(255),
  ADD CONSTRAINT fk_posts_users foreign key(user_id) references users(id);