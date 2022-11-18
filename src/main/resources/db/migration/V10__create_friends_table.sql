DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  id bigserial PRIMARY KEY,
  to_user bigint REFERENCES users(id) NOT NULL,
  constraint fk_friends_to_user foreign key (to_user) REFERENCES users(id),
  from_user bigint REFERENCES users(id) NOT NULL,
  constraint fk_friends_from_user foreign key (from_user) REFERENCES users(id),
  confirmed integer DEFAULT 0
);

INSERT INTO users (username, password, enabled)
VALUES
('user1', '123', TRUE),
('user2', '123', TRUE),
('user3', '123', TRUE),
('user4', '123', TRUE);

INSERT INTO friends (to_user, from_user, confirmed)
VALUES
(3, 4, 0),
(3, 1, 0),
(3, 2, 0);

-- INSERT INTO
-- 	projects(name, start_date, end_date)
-- VALUES
-- 	('AI for Marketing','2019-08-01','2019-12-31'),
-- 	('ML for Sales','2019-05-15','2019-11-20');
