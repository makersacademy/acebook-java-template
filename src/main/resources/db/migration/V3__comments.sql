DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  time timestamp default CURRENT_TIMESTAMP NOT NULL,
  user_id bigserial NOT NULL,
  post_id bigserial NOT NULL,
  constraint fk_user_id foreign key(user_id) references users(id),
  constraint fk_post_id foreign key(post_id) references posts(id)
);
