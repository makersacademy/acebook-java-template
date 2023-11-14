ALTER TABLE posts
ADD COLUMN timestamp timestamp,
ADD COLUMN user_id integer,
ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);


ALTER TABLE users
ADD image_url varchar(500);


CREATE TABLE likes (
  id bigserial PRIMARY KEY,
  like_status boolean DEFAULT false,
  user_id integer,
  post_id integer,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (post_id) REFERENCES posts(id),
  UNIQUE (user_id, post_id)
);


CREATE TABLE comments (
  id bigserial PRIMARY KEY,
  comment varchar(281) NOT NULL,
  post_id integer,
  user_id integer,
  FOREIGN KEY (post_id) REFERENCES posts(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE friends (
  id bigserial PRIMARY KEY,
  requester_id integer,
  receiver_id integer,
  FOREIGN KEY (requester_id) REFERENCES users(id),
  FOREIGN KEY (receiver_id) REFERENCES users(id),
  status varchar(50) check (status in ('ACCEPTED', 'PENDING', 'DENIED'))
);