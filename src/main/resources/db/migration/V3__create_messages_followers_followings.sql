DROP TABLE IF EXISTS messages;

CREATE TABLE messages (
  sender INTEGER REFERENCES users (id) NOT NULL,
  receiver INTEGER REFERENCES users (id) NOT NULL,
  message TEXT NOT NULL,
  time TIMESTAMP NOT NULL
);

CREATE TABLE followers (
  user_id INTEGER REFERENCES users (id) NOT NULL,
  follower_id INTEGER REFERENCES users (id) NOT NULL,
  time TIMESTAMP NOT NULL
);

CREATE TABLE followings (
  user_id INTEGER REFERENCES users (id) NOT NULL,
  following_id INTEGER REFERENCES users (id) NOT NULL,
  time TIMESTAMP NOT NULL
);