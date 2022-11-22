DROP TABLE IF EXISTS profiles;

CREATE TABLE profiles (
  id bigserial PRIMARY KEY,
  user_id bigint REFERENCES users(id) NOT NULL,
  constraint fk_friends_user_id foreign key (user_id) REFERENCES users(id),
  bio VARCHAR(250),
  pronouns text,
  birthday date,
  nickname text,
  cover_image text
);
