ALTER TABLE authorities
  DROP CONSTRAINT fk_authorities_users,
  ADD COLUMN user_id bigint;

UPDATE authorities
   SET user_id = users.id
   FROM authorities as auth
   INNER JOIN  users ON auth.username = users.username;

ALTER TABLE authorities
  ADD CONSTRAINT fk_authorities_users foreign key(user_id) references users(id),
  ALTER COLUMN user_id SET NOT NULL,
  ADD UNIQUE (user_id),
  DROP COLUMN username;