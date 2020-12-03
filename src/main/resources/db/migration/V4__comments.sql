DROP TABLE IF EXISTS comments;

CREATE TABLE comments (
 id bigserial PRIMARY KEY,
 created_at TIMESTAMP DEFAULT Now(),
 content varchar(250) NOT NULL,
 user_id BIGINT,
 FOREIGN KEY(user_id) REFERENCES users(id),
 post_id BIGINT,
 FOREIGN KEY(post_id) REFERENCES posts(id)
);
