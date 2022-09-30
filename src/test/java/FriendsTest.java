import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.makersacademy.acebook.Application;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

// TESTS INCOMPLETE

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FriendsTest {
  @Autowired
  UserRepository userRepository;
  
  // You need .valueOf for Long values (similar to Integer), otherwise you get an "ambiguous" error

  @Test
  public void findByUserNameReturnsCorrectID() {
    User testUser = userRepository.findByUserName("Benjamin");
    assertEquals(Long.valueOf(1L), testUser.getId());
  }

  // getRequestStatus Method Tests

  @Test
  public void getRequestStatusReturnsAccepted() {
    String testRequestStatus = userRepository.getRequestStatus(Long.valueOf(1L), Long.valueOf(4L));
    assertEquals("accepted", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsPending() {
    String testRequestStatus = userRepository.getRequestStatus(12L, 9L);
    assertEquals("pending", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsBlocked() {
    String testRequestStatus = userRepository.getRequestStatus(5L, 6L);
    assertEquals("blocked", testRequestStatus);
  }

  // getFriends Method Tests

  @Test
  public void getFriendsReturnsCorrectNumberOfFriends() {
    ArrayList<User> testList1 = new ArrayList<User>(); 
    Iterable<User> testFriends1 = userRepository.getFriends(Long.valueOf("7"));
    testFriends1.forEach(u -> testList1.add(u));
    assertEquals(2, testList1.size());
  }

  @Test
  public void getFriendsReturnsCorrectUserNames() {
    ArrayList<User> testList2 = new ArrayList<User>();
    Iterable<User> testFriends2 = userRepository.getFriends(Long.valueOf("1"));
    testFriends2.forEach(u -> testList2.add(u));
    assertEquals("Xiaoyu",testList2.get(0).getUsername());
    assertEquals("Eoin", testList2.get(1).getUsername());
  }

  // getFriendRequests Method Tests

  @Test
  public void getFriendRequestsReturnsCorrectNumberOfRequests() {
    ArrayList<User> testList3 = new ArrayList<User>();
    Iterable<User> testFriends3 = userRepository.getFriendRequests(1L);
    testFriends3.forEach(user -> testList3.add(user));
    assertEquals(2, testList3.size());
  }

  @Test
  public void getFriendRequestsReturnsCorrectUserNames() {
    ArrayList<User> testList4 = new ArrayList<User>();
    Iterable<User> testFriends4 = userRepository.getFriendRequests(12L);
    testFriends4.forEach(user -> testList4.add(user));
    assertEquals("Scarlet", testList4.get(0).getUsername());
  }
}
