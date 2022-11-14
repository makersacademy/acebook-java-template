ALTER TABLE posts
ADD CONSTRAINT fk_posts_users foreign key(user_id)
  references users(id);
