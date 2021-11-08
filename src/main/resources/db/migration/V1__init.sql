DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  created_date timestamp NOT NULL,
  content varchar(250) NOT NULL
);
