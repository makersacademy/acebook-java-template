package com.makersacademy.acebook.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import java.sql.Timestamp;
import javax.persistence.Column;
import org.junit.Test;

public class PostTest {

	@Data
	@Entity
	@Table(name = "posts")

	private Post post = new Post("hello", 0);

	@Column(name="time_created")			//database column name? (time_created?)
	private Timestamp time_created;

	public void setTimeCreated(Timestamp time_created) {this.time_created = time_created; }


	@Test
	public void postHasContent() {
		assertThat(post.getContent(), containsString("hello"));
	}


}
