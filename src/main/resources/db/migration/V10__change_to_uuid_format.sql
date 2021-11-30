ALTER TABLE posts DROP COLUMN id CASCADE;
ALTER TABLE posts DROP COLUMN userid CASCADE;
ALTER TABLE posts ADD COLUMN postID uuid PRIMARY KEY;
ALTER TABLE users DROP COLUMN id CASCADE;
ALTER TABLE users ADD COLUMN userID uuid PRIMARY KEY;
ALTER TABLE comments DROP COLUMN id CASCADE;
ALTER TABLE comments DROP COLUMN userid CASCADE;
ALTER TABLE comments DROP COLUMN postid CASCADE;
ALTER TABLE comments ADD COLUMN commentID uuid PRIMARY KEY;
ALTER TABLE posts ADD COLUMN userid uuid references users(userID);
ALTER TABLE comments ADD COLUMN userid uuid references users(userID);
ALTER TABLE comments ADD COLUMN postid uuid references posts(postid);
