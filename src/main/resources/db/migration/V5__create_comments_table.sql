DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  content varchar(250) NOT NULL,
  user_id BIGINT NOT NULL,
  post_id BIGINT NOT NULL,
  time_posted TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_comments_users FOREIGN KEY(user_id) REFERENCES users(id),
  FOREIGN KEY (post_id) REFERENCES posts(id),
  CONSTRAINT fk_comments_posts FOREIGN KEY(post_id) REFERENCES posts(id)
);