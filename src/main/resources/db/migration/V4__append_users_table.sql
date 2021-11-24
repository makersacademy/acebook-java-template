ALTER TABLE users
    ALTER COLUMN password TYPE varchar(60),
    ALTER COLUMN password SET NOT NULL;