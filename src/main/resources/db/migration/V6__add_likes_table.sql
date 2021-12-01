CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  constraint fk_likes_users foreign key(username) references users(username)
);
