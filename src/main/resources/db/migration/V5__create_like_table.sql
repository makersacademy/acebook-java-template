
CREATE TABLE liked_posts(
user_id bigint,
post_id bigint,

 FOREIGN KEY(post_id)
   REFERENCES
 FOREIGN KEY(user_id) 
	  REFERENCES users(id)
);