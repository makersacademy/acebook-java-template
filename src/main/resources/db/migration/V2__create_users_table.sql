
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  name varchar(50) NOT NULL,
  password varchar(60) NOT NULL,
  email TEXT NOT NULL UNIQUE,
  about TEXT NULL, 
  imageUrl TEXT NULL,
  passwordDigest TEXT NOT NULL,
  time TIMESTAMP NOT NULL 
);
CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  email varchar(50) REFERENCES users(email),
  authority varchar(50) NOT NULL
);

create unique index ix_auth_email on authorities(email, authority);