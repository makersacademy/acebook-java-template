ALTER TABLE friends
ALTER COLUMN status_code TYPE boolean USING status_code::boolean;