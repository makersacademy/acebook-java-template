CREATE TABLE friends (
id bigserial PRIMARY KEY,
person_id bigint NOT NULL,
friend_id bigint NOT NULL,
accepted boolean NOT NULL,
FOREIGN KEY(person_id) REFERENCES users(id),
FOREIGN KEY(friend_id) REFERENCES users(id)
);