CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  user_id BIGINT REFERENCES users (id) NOT NULL,
  post_id BIGINT REFERENCES posts (id) NOT NULL,
  time TIMESTAMP NOT NULL
);

ALTER TABLE posts
ADD likes_count BIGINT NULL;