DROP TABLE IF EXISTS messages;

CREATE TABLE messages (
  sender INTEGER REFERENCES users (id) NOT NULL,
  receiver INTEGER REFERENCES users (id) NOT NULL,
  message TEXT NOT NULL,
  time TIMESTAMP NOT NULL
);

CREATE TABLE followers (
  userId INTEGER REFERENCES users (id) NOT NULL,
  followerId INTEGER REFERENCES users (id) NOT NULL,
  time TIMESTAMP NOT NULL
);

CREATE TABLE followings (
  userId INTEGER REFERENCES users (id) NOT NULL,
  followingId INTEGER REFERENCES users (id) NOT NULL,
  time TIMESTAMP NOT NULL
);