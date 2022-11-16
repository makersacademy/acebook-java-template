DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  likes int NULL,
  user_id bigint NOT NULL,
  time_created timestamp NOT NULL,
  constraint fk_user_id foreign key(user_id) references users(id)
);

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  user_id bigint NOT NULL,
  post_id int NOT NULL,
  constraint fk_user_id foreign key(user_id) references users(id),
  constraint fk_post_id foreign key(post_id) references posts(id)
);