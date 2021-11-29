CREATE TABLE comments (
                       id bigserial PRIMARY KEY,
                       content VARCHAR(250) NOT NULL,
                       stamp TIMESTAMP,
                       likes integer,
                       userID BIGSERIAL NOT NULL references users(id),
                       username VARCHAR(50) NOT NULL references users(username),
                       postID BIGSERIAL NOT NULL  references posts(id)
);