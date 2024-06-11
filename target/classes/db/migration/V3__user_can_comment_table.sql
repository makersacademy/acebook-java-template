CREATE TABLE comments (

    comment_id int,
    text VARCHAR(255),
    user_id int,
    PRIMARY KEY(comment_id),
    FOREIGN KEY(user_id) REFERENCES users(id)

);