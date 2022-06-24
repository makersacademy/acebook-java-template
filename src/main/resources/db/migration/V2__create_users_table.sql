
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(60) NOT NULL,
  email TEXT NOT NULL UNIQUE,
  name varchar(50) NOT NULL,
  about TEXT NULL, 
  image_url TEXT NULL,
  enabled BOOLEAN NOT NULL,
  time TIMESTAMP NOT NULL 
);

CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  username varchar(50) REFERENCES users(username),
  authority varchar(50) NOT NULL
);

create unique index ix_auth_username on authorities(username, authority);