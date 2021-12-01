TRUNCATE posts RESTART IDENTITY CASCADE;
ALTER TABLE posts
    ADD COLUMN comment_condition boolean default false not null;

CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  postId bigserial NOT NULL,
  comment varchar(50) NOT NULL,
  commenter varchar(50) NOT NULL,
  constraint fk_posts foreign key(postId) references posts(id)
);




