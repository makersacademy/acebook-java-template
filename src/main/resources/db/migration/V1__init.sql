DROP TABLE IF EXISTS events;

CREATE TABLE events (
  id bigserial PRIMARY KEY,
  title varchar(250) NOT NULL,
  description varchar(250) NOT NULL,
  scheduled_date timestamp NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
