ALTER TABLE likes ADD COLUMN username varchar(50) NOT NULL references users(username);
ALTER TABLE likes DROP COLUMN user_id;
