DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
    id bigserial PRIMARY KEY,
    status_code varchar(50) NOT NULL,
    request_from bigserial,
    request_to bigserial,
    sent_at TIMESTAMP
);
