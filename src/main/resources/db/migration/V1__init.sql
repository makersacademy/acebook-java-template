
DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  message TEXT NOT NULL,
  message_url TEXT NULL,
  time TIMESTAMP NOT NULL
);
