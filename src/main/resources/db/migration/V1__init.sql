DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id BIGSERIAL PRIMARY KEY,
  title varchar(250) NOT NULL,
  content varchar(250) NOT NULL
);