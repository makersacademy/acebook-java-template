DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL
);

CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  constraint fk_authorities_users foreign key(username) references users(username)
);

create unique index ix_auth_username on authorities(username, authority);

INSERT INTO users(username, password, enabled)
VALUES ('giorgio', '123456', true);
INSERT INTO users(username, password, enabled)
VALUES ('asimina', '123456', true);
INSERT INTO users(username, password, enabled)
VALUES ('danny', '123456', true);
INSERT INTO users(username, password, enabled)
VALUES ('dogancan', '123456', true);
INSERT INTO users(username, password, enabled)
VALUES ('hisham', '123456', true);
INSERT INTO users(username, password, enabled)
VALUES ('thomas', '123456', true);

INSERT INTO authorities(username, authority)
VALUES ('giorgio', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('asimina', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('danny', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('dogancan', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('hisham', 'ROLE_USER');
INSERT INTO authorities(username, authority)
VALUES ('thomas', 'ROLE_USER');