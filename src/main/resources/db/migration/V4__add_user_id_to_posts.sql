ALTER TABLE posts ADD userid bigint;
ALTER TABLE posts ADD FOREIGN KEY (userid) REFERENCES users(id);