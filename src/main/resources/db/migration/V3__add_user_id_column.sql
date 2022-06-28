ALTER TABLE posts
add user_id bigint references users(id) on delete cascade NOT NULL; 
