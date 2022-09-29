DROP TABLE IF EXISTS messages;

CREATE TABLE messages (
  message_id bigserial PRIMARY KEY,
  txt varchar(300),
  sender_id int8,
  recipient_id int8
);