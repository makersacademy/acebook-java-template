CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  post_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_post
  FOREIGN KEY (post_id) REFERENCES posts(id),
  CONSTRAINT fk_comment_user
  FOREIGN KEY (user_id) REFERENCES users(id)
);