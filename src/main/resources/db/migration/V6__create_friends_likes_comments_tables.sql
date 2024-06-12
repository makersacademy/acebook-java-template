CREATE TABLE friends (
  id bigserial PRIMARY KEY,
  sender_id bigint NOT NULL,
  recipient_id bigint NOT NULL,
  accepted BOOLEAN DEFAULT FALSE,
  constraint fk_friends_sender_users foreign key(sender_id) references users(id),
  constraint fk_friends_recipient_users foreign key(recipient_id) references users(id)
);

CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  post_id bigint NOT NULL,
  user_id bigint NOT NULL,
  constraint fk_likes_posts foreign key(post_id) references posts(id),
  constraint fk_likes_users foreign key(user_id) references users(id)
);

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  post_id bigint NOT NULL,
  user_id bigint NOT NULL,
  content text NOT NULL,
  constraint fk_comments_posts foreign key(post_id) references posts(id),
  constraint fk_comments_users foreign key(user_id) references users(id)
);
