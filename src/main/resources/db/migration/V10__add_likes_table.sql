CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  post_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_like_post
    FOREIGN KEY (post_id) REFERENCES posts(id),
  CONSTRAINT fk_like_user
    FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT unique_like_per_user_per_post UNIQUE (post_id, user_id)
);
