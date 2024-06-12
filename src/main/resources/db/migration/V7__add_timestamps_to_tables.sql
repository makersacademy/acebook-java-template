ALTER TABLE authorities
  ADD COLUMN created_at timestamp default now();

ALTER TABLE comments
  ADD COLUMN created_at timestamp default now();

ALTER TABLE friends
  ADD COLUMN created_at timestamp default now();

ALTER TABLE likes
  ADD COLUMN created_at timestamp default now();

ALTER TABLE posts
  ADD COLUMN created_at timestamp default now();

ALTER TABLE users
  ADD COLUMN created_at timestamp default now();