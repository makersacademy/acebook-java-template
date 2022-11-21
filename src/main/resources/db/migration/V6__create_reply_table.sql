DROP TABLE IF EXISTS replies;

CREATE TABLE replies (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  user_id INTEGER references users(id) NOT NULL,
  username TEXT,
  post_id INTEGER REFERENCES posts(id),
  constraint fk_posts_id foreign key (post_id) REFERENCES posts(id),
  CONSTRAINT fk_likes_user foreign key(user_id) references users(id)
);