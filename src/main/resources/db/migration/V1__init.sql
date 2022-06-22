DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  message TEXT NOT NULL,
  messageUrl TEXT NULL,
  time TIMESTAMP NOT NULL
);
