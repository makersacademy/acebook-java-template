DROP TABLE IF EXISTS replies;

CREATE TABLE replies (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  post_id INTEGER REFERENCES posts(id),
  constraint fk_posts_id foreign key (post_id) REFERENCES posts(id)
);