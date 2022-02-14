DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  message VARCHAR(250),
  postsid BIGINT REFERENCES posts (id),
  usersid BIGINT REFERENCES users (id)
);