DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  time timestamp default CURRENT_TIMESTAMP NOT NULL,
  user_id bigserial NOT NULL,
  constraint fk_user_id foreign key(user_id) references users(id)
);
