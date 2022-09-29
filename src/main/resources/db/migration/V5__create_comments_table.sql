DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  content varchar(250) NOT NULL,
  userid BIGINT NOT NULL,
  postid BIGINT NOT NULL,
  time_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (userid) REFERENCES users(id),
  CONSTRAINT fk_comments_users FOREIGN KEY(userid) REFERENCES users(id),
  FOREIGN KEY (postid) REFERENCES posts(id),
  CONSTRAINT fk_comments_posts FOREIGN KEY(postid) REFERENCES posts(id)
);