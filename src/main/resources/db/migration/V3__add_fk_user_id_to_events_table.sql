ALTER TABLE events ADD COLUMN user_id BIGINT;

ALTER TABLE events ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES users(id);
