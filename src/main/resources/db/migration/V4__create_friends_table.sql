DROP TABLE IF EXISTS friends;

CREATE TABLE friends (
  friendship_id bigserial PRIMARY KEY,
  requester_id int8,
  requestee_id int8,
  request_status varchar(8),
  low_id int8,
  high_id int8,
  -- ^ request_status is either "accepted" or "pending" or "blocked". Rejections will delete entry in this table
  constraint fk_requester_users foreign key(requester_id) references users(id),
  constraint fk_requestee_users foreign key(requestee_id) references users(id),
  -- ^ both of these reference users.id
  constraint ck_requester_requestee_not_same check (low_id <> high_id),
  -- ^ checks that requester_id and requestee_id can't be the same
  constraint uq_requester_requestee_pair unique (low_id, high_id)
  -- ^ checks that low_id and high_id pairing is unique
);