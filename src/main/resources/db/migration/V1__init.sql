DROP TABLE IF EXISTS posts, comments;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL
);
