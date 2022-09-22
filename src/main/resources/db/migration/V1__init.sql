DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  title varchar(250) NOT NULL,
  content varchar(250) NOT NULL
);
