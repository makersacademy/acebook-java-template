CREATE TABLE comments_likes (
  id bigserial PRIMARY KEY,
  comment_id bigint NOT NULL,
  user_id bigint NOT NULL,
  constraint fk_comments_like_comment foreign key(comment_id) references comments(id),
  constraint fk_likes_users foreign key(user_id) references users(id)
);