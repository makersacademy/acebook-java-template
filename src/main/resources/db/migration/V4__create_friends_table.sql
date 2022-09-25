DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  friendship_id bigserial PRIMARY KEY,
  requester_id int8,
  requestee_id int8,
  request_status varchar(8),
  -- ^ request_status is either "accepted" or "pending" or "blocked". Rejections will delete entry in this table
  constraint fk_requester_users foreign key(requester_id) references users(id),
  constraint fk_requestee_users foreign key(requestee_id) references users(id)
  -- ^ both of these reference users.id
);