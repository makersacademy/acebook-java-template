DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
  userid BIGINT REFERENCES users(id) NOT NULL,
  postid BIGINT REFERENCES posts(id) NOT NULL,
  PRIMARY KEY (userid, postid)
);
