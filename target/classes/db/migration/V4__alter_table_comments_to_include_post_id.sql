ALTER TABLE comments
ADD COLUMN post_id INT,
ADD CONSTRAINT fk_post_id
FOREIGN KEY (post_id)
REFERENCES posts(id);
