DROP TABLE IF EXISTS likes;
ALTER TABLE comments DROP COLUMN likes;
ALTER TABLE posts DROP COLUMN likes;
CREATE TABLE likes(
    likeID uuid PRIMARY KEY
);
ALTER TABLE likes ADD COLUMN userid uuid references users(userID);
ALTER TABLE likes ADD COLUMN postid uuid NULL references posts(postid);
ALTER TABLE likes ADD COLUMN commentid uuid NULL references comments(commentid);
ALTER TABLE likes ADD COLUMN username VARCHAR(50) references users(username);
