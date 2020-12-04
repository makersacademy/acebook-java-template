CREATE TABLE users (
    id bigserial PRIMARY KEY,
    email varchar(100) NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    password varchar(100) NOT NULL
    );


CREATE TABLE user_roles (
    id bigserial PRIMARY KEY,
    USER_EMAIL varchar(100) NOT NULL,
    ROLE_NAME varchar(100) NOT NULL
    );
