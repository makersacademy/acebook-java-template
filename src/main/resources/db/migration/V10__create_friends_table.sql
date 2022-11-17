DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  id bigserial PRIMARY KEY,
  to_user bigint REFERENCES users(id) NOT NULL,
  constraint fk_friends_to_user foreign key (to_user) REFERENCES users(id),
  from_user bigint REFERENCES users(id) NOT NULL,
  constraint fk_friends_from_user foreign key (from_user) REFERENCES users(id),
  confirmed integer DEFAULT 0
);
