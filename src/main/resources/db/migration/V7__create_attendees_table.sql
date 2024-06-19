DROP TABLE IF EXISTS attendees;

CREATE TABLE attendees (
  id bigserial PRIMARY KEY,
  user_id BIGINT NOT NULL,
  constraint fk_attendees_users foreign key(user_id) references users(id),
  event_id BIGINT NOT NULL,
  constraint fk_attendees_events foreign key(event_id) references events(id)
);
