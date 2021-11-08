DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  usern varchar(250),
  content varchar(250) NOT NULL,
  time timestamp default CURRENT_TIMESTAMP NOT NULL,
  user_id bigserial NOT NULL,
  constraint fk_posts_users foreign key(user_id) references users(id) 
);

-- create unique index ix_post_username on posts(content,time, username);

