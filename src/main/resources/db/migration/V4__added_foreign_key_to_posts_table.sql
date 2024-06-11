ALTER TABLE posts
    ADD COLUMN user_id int,
    ADD CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
;
