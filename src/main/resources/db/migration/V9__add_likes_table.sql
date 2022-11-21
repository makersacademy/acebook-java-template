DROP TABLE IF EXISTS likes;

CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  user_id bigint references users(id) NOT NULL,
  post_id bigint references posts(id),

  CONSTRAINT fk_likes_user foreign key(user_id) references users(id),
  CONSTRAINT fk_likes_post foreign key(post_id) references posts(id)
);