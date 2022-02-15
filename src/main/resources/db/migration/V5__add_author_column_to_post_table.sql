ALTER TABLE posts ADD author VARCHAR(50) NOT NULL;
ALTER TABLE posts ADD CONSTRAINT fk_author_to_user FOREIGN KEY (author) REFERENCES users(username); 
