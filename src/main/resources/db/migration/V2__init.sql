DROP TABLE IF EXISTS posts;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  usern varchar(250),
  content varchar(250) NOT NULL,
  time timestamp default CURRENT_TIMESTAMP NOT NULL,
  username varchar(250),
  constraint fk_posts_users foreign key(username) references users(username) 
);

create unique index ix_post_username on posts(content,time, username);

