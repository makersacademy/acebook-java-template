DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
    id SERIAL PRIMARY KEY,
    user_id bigserial NOT NULL REFERENCES users(id),
    friend_id bigserial NOT NULL REFERENCES users(id),
    UNIQUE (user_id, friend_id)
);