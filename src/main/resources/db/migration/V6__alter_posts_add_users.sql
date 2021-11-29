ALTER TABLE posts ADD COLUMN userID BIGSERIAL NOT NULL references users(id);

ALTER TABLE posts ADD COLUMN username VARCHAR(50) NOT NULL references users(username);