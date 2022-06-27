DROP TABLE followers;

DROP TABLE followings;

CREATE TABLE friends (
  id bigserial PRIMARY KEY,
  user_id BIGINT REFERENCES users (id) NOT NULL,
  friend_id BIGINT REFERENCES users (id) NOT NULL,
  accepted BOOLEAN NOT NULL,
  visibility TEXT NOT NULL,
  time TIMESTAMP NOT NULL
);

