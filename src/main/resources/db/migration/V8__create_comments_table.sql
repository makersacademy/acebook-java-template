DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  content VARCHAR(250) NOT NULL,
  timestamp TIMESTAMP,
  userid BIGINT REFERENCES users(id),
  postid BIGINT REFERENCES posts(id)
);
