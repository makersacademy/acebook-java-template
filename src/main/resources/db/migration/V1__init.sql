DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  likes INTEGER
);
