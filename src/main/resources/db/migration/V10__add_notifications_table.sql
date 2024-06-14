CREATE TABLE notifications (
  id bigserial PRIMARY KEY,
  user_id bigint NOT NULL,
  type varchar(255),
  message varchar(255),
  link varchar(255),
  seen BOOLEAN DEFAULT FALSE,
  created_at timestamp default now(),
  constraint fk_friends_sender_users foreign key(user_id) references users(id)
);