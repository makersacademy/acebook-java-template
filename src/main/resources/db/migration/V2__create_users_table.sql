DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  enabled boolean NOT NULL
);
