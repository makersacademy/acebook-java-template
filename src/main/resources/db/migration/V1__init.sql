DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  usern varchar(250),
  content varchar(250) NOT NULL,
  time timestamp default CURRENT_TIMESTAMP NOT NULL 
);
