DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  content text NOT NULL,
  created_at timestamp DEFAULT now() NOT NULL,
  user_id BIGINT NOT NULL,
  constraint fk_comments_users foreign key(user_id) references users(id),
  event_id BIGINT NOT NULL,
  constraint fk_comments_events foreign key(event_id) references events(id)
);