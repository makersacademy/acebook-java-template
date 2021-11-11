DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  timestamp VARCHAR(255),
  photo varchar(250),
  username VARCHAR(50)
);
