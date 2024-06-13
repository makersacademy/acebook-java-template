ALTER TABLE comments_likes
  ADD COLUMN created_at timestamp default now();