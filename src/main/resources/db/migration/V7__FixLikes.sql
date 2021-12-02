ALTER TABLE posts DROP COLUMN IF EXISTS like_id ;
ALTER TABLE likes DROP CONSTRAINT IF EXISTS fk_likes_posts;
ALTER TABLE likes DROP COLUMN IF EXISTS id;
ALTER TABLE likes DROP COLUMN IF EXISTS id;
ALTER TABLE likes DROP COLUMN IF EXISTS post_id;
ALTER TABLE likes ADD COLUMN  like_id bigserial PRIMARY KEY;
ALTER TABLE likes ADD COLUMN  id bigserial NOT NULL;
ALTER TABLE likes ADD constraint fk_likes_post foreign key(id) references posts(id);
