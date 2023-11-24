DROP TABLE IF EXISTS friendships;

CREATE TABLE friendships (
  id bigserial PRIMARY KEY,
  user_requester BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  user_accepter BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  status varchar(250) NOT NULL
);