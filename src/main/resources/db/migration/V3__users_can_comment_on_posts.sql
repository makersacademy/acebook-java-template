DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  message VARCHAR(250),
  posts_id BIGINT REFERENCES posts (id),
  users_id BIGINT REFERENCES users (id)
);