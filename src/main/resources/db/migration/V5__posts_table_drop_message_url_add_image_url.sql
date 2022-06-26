ALTER TABLE posts
DROP COLUMN message_url;

ALTER TABLE posts
ADD image_url TEXT NULL;