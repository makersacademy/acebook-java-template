TRUNCATE posts RESTART IDENTITY CASCADE;
ALTER TABLE posts ADD username varchar(50) NOT NULL;
ALTER TABLE posts ADD likes int NOT NULL;
ALTER TABLE posts ADD   constraint fk_posts_users foreign key(username) references users(username);

CREATE TABLE profile (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  profile_picture varchar(300) NOT NULL,
  constraint fk_profile_users foreign key(username) references users(username)
);



