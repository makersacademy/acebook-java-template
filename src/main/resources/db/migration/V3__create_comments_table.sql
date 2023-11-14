DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  post_id INT NOT NULL REFERENCES posts(id) ON DELETE CASCADE,
  content varchar(250) NOT NULL
);