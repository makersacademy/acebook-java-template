-- Drop the table if it exists
DROP TABLE IF EXISTS posts;

-- Create the table with the new column
CREATE TABLE posts (
  id bigserial PRIMARY KEY,
  content varchar(250) NOT NULL,
  likes BIGINT DEFAULT 0
);