DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(100) NOT NULL,
  enabled boolean NOT NULL
);

CREATE TABLE authorities (
  id BIGSERIAL PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  constraint fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE likes (
  id BIGSERIAL PRIMARY KEY,
  likedPost BIGINT NOT NULL,
  username varchar(50) NOT NULL,
  FOREIGN KEY (likedPost) REFERENCES posts(id),
  CONSTRAINT fk_likes_users FOREIGN KEY(username) REFERENCES users(username)
);

create unique index ix_auth_username on authorities(username, authority);