DROP TABLE IF EXISTS comments ;

CREATE TABLE comments (
    id bigserial PRIMARY KEY,
    content text NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id bigint,
        CONSTRAINT fk_user FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
    post_id bigint,
        CONSTRAINT fk_post FOREIGN KEY (post_id)
        REFERENCES posts(id)
        ON DELETE CASCADE
);