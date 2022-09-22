DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(50) NOT NULL,
  -- enabled is automatically set to TRUE by Spring Security
  -- if this is changed to FALSE
  -- the user won't be able to log in anymore
  enabled boolean NOT NULL
);

CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities(username, authority);