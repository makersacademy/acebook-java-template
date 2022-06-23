ALTER TABLE posts
ADD username varchar(50),
ADD constraint fk_posts_users foreign key(username) references users(username);

create unique index ix_posts_username on posts(username, content);

