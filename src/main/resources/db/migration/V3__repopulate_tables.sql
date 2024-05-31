
DROP TABLE IF EXISTS posts CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS comments CASCADE;
DROP TABLE IF EXISTS authorities CASCADE;

CREATE TABLE users (
  id bigserial PRIMARY KEY,
  firstname TEXT,
  lastname TEXT,
  email VARCHAR(250),
  bio TEXT,
  username varchar(50) NOT NULL UNIQUE,
  password varchar(50) NOT NULL,
  enabled boolean NOT NULL
);


CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  like_count INTEGER,
  user_id BIGINT,
  CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE authorities (
  id bigserial PRIMARY KEY,
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  constraint fk_authorities_users foreign key(username) references users(username) ON DELETE CASCADE
);

create unique index ix_auth_username on authorities(username, authority);

CREATE TABLE comments(
	id BIGSERIAL PRIMARY KEY,
	content TEXT,
	post_id BIGSERIAL,
	user_id BIGSERIAL,
	CONSTRAINT fk_post_id FOREIGN KEY(post_id) REFERENCES posts(id) ON DELETE CASCADE,
	CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO users (firstname, lastname, email, bio, username, password, enabled) VALUES
('John', 'Doe', 'john.doe@example.com', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'johndoe', 'password123', TRUE),
('Jane', 'Smith', 'jane.smith@example.com', 'Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 'janesmith', 'password456', TRUE),
('Alice', 'Johnson', 'alice.johnson@example.com', 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.', 'alicej', 'password789', TRUE),
('Bob', 'Brown', 'bob.brown@example.com', 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.', 'bobbrown', 'password101', TRUE),
('Charlie', 'Davis', 'charlie.davis@example.com', 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'charlied', 'password202', TRUE),
('Diana', 'Evans', 'diana.evans@example.com', 'Curabitur pretium tincidunt lacus. Nulla gravida orci a odio.', 'dianae', 'password303', TRUE),
('Edward', 'Franklin', 'edward.franklin@example.com', 'Mauris lacinia lacus sed turpis tincidunt id iaculis mauris malesuada.', 'edwardf', 'password404', TRUE),
('Fiona', 'Green', 'fiona.green@example.com', 'Suspendisse potenti. Sed vestibulum lectus sit amet sapien.', 'fionag', 'password505', TRUE),
('George', 'Harris', 'george.harris@example.com', 'Integer in libero sit amet mi posuere mattis.', 'georgeh', 'password606', TRUE),
('Helen', 'Ivory', 'helen.ivory@example.com', 'Aenean id metus id velit ullamcorper pulvinar.', 'heleni', 'password707', TRUE);

INSERT INTO posts (content, like_count, user_id) VALUES
('This is my first post!', 15, 1),
('Hello world!', 30, 2),
('What a wonderful day!', 10, 3),
('Just had a great lunch.', 5, 4),
('Excited to be here!', 25, 1),
('Reading a fantastic book.', 20, 5),
('Loving the new features!', 35, 6),
('Working on a new project.', 50, 7),
('Happy Friday everyone!', 45, 8),
('Feeling grateful today.', 40, 9),
('Good morning!', 60, 10),
('Had an amazing weekend!', 70, 2),
('Can’t wait for the holidays.', 65, 3),
('Best coffee ever!', 55, 4),
('Enjoying the sunshine.', 25, 5),
('Just completed a 5K run!', 75, 6),
('Learning new things every day.', 85, 7),
('New blog post up now!', 95, 8),
('Thrilled with my progress.', 100, 9),
('Looking forward to the weekend.', 110, 10);

INSERT INTO authorities (username, authority) VALUES
('johndoe', 'ROLE_USER'),
('janesmith', 'ROLE_USER'),
('alicej', 'ROLE_USER'),
('bobbrown', 'ROLE_USER'),
('charlied', 'ROLE_USER'),
('dianae', 'ROLE_USER'),
('edwardf', 'ROLE_USER'),
('fionag', 'ROLE_USER'),
('georgeh', 'ROLE_USER'),
('heleni', 'ROLE_USER');

INSERT INTO comments (content, post_id, user_id) VALUES
('Great post!', 1, 2),
('I totally agree!', 2, 3),
('Nice picture!', 3, 4),
('Well said.', 4, 5),
('Thanks for sharing.', 5, 1),
('Interesting perspective.', 6, 7),
('Loved this post!', 7, 8),
('Very informative.', 8, 9),
('Keep it up!', 9, 10),
('Thanks for the update.', 10, 1),
('Fantastic read!', 11, 2),
('Enjoyed this.', 12, 3),
('This was helpful.', 13, 4),
('I learned something new.', 14, 5),
('Brilliant!', 15, 6),
('I appreciate this.', 16, 7),
('Looking forward to more.', 17, 8),
('This is great!', 18, 9),
('Nice post!', 19, 10),
('Loved it!', 20, 1);