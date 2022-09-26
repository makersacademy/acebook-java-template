ALTER TABLE comments ADD user_id bigint references users(id);
