DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS comments;

CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  likes int NOT NULL,
  user_id bigint NOT NULL,
  time_created timestamp NOT NULL,
  constraint fk_user_id foreign key(user_id) references users(id)
);

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  user_id bigint,
  post_id bigint,
  constraint fk_user_id foreign key(user_id) references users(id)
 -- constraint fk_post_id foreign key(post_id) references posts(id)
);


INSERT INTO posts(content, likes, user_id, time_created)
VALUES('THIS IS A CONTENT', 0, 2, '2022/11/01');
INSERT INTO posts(content, likes, user_id, time_created)
VALUES('post 1', 0, 1, '2022/11/05');
INSERT INTO posts(content, likes, user_id, time_created)
VALUES('post 2', 0, 3, '2022/11/10');
INSERT INTO posts(content, likes, user_id, time_created)
VALUES('post 3', 0, 4, '2022/11/15');
INSERT INTO posts(content, likes, user_id, time_created)
VALUES('post 4', 0, 5, '2022/11/18');


INSERT INTO comments(content, user_id, post_id)
VALUES('comment 1', 1, 2);
INSERT INTO comments(content, user_id, post_id)
VALUES('comment 2', 4, 2);
INSERT INTO comments(content, user_id, post_id)
VALUES('comment 3', 3, 4);
INSERT INTO comments(content, user_id, post_id)
VALUES('comment 4', 2, 4);
INSERT INTO comments(content, user_id, post_id)
VALUES('comment 4', 1, 2);
