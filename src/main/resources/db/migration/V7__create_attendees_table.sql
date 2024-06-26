DROP TABLE IF EXISTS attendees;

CREATE TABLE attendees (
  id bigserial PRIMARY KEY,
  user_id BIGINT NOT NULL,
  CONSTRAINT fk_attendees_users FOREIGN KEY(user_id) REFERENCES users(id),
  event_id BIGINT NOT NULL,
  CONSTRAINT fk_attendees_events FOREIGN KEY(event_id) REFERENCES events(id)
);
