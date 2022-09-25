import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// @DataJpaTest
public class FriendsTest {
  @Autowired
  UserRepository userRepository;

  // The below will build a table with the schema details below in the test database
  @Entity
  @Table(name = "users")
  public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="username", nullable=false, unique=false)
    private String username;

    @Column(name="password", nullable=false, unique=false)
    private String password;
  }

  @Test
  public void getRequestStatusReturnsAccepted() {
    String requestStatus = userRepository.getRequestStatus(1, 4);
    assertEquals("accepted",requestStatus);
  }

  @Test
  public void getRequestStatusReturnsPending() {
    String requestStatus = userRepository.getRequestStatus(12, 9);
    assertEquals("pending",requestStatus);
  }

  @Test
  public void getRequestStatusReturnsBlocked() {
    String requestStatus = userRepository.getRequestStatus(5, 6);
    assertEquals("blocked",requestStatus);
  }
}
