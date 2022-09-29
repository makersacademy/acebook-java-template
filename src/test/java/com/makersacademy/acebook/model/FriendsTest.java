package com.makersacademy.acebook.model;

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
import javax.persistence.*;

// TESTS INCOMPLETE

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FriendsTest {
  @Autowired
  UserRepository userRepository;

  // The below will build a table with the schema details below in the test
  // database
  @Entity
  @Table(name = "users")
  public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = false)
    private String username;

    @Column(name = "password", nullable = false, unique = false)
    private String password;
  }

  @Test
  public void findByUserNameReturnsCorrectID() {
    // Having the full path here is necessary because "User" refers to the User
    // entity defined above otherwise (want the User model class here)
    // ^ You don't need an "import" statement if you do this
    com.makersacademy.acebook.model.User testUser = userRepository.findByUserName("boris");
    assertEquals(Long.valueOf(13L), testUser.getId());
    // You need .valueOf for Long values (similar to Integer), otherwise you get an
    // "ambiguous" error
  }

  // getRequestStatus Method Tests
  // NOTE: "Reversed" tests for getRequest Status below are checking that the
  // order of users given as parameters doesn't matter

  @Test
  public void getRequestStatusReturnsAccepted() {
    String testRequestStatus = userRepository.getRequestStatus(Long.valueOf(1L), Long.valueOf(4L));
    assertEquals("accepted", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsAcceptedReversed() {
    String testRequestStatus = userRepository.getRequestStatus(Long.valueOf(4L), Long.valueOf(1L));
    assertEquals("accepted", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsPending() {
    String testRequestStatus = userRepository.getRequestStatus(12L, 9L);
    assertEquals("pending", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsPendingReversed() {
    String testRequestStatus = userRepository.getRequestStatus(9L, 12L);
    assertEquals("pending", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsBlocked() {
    String testRequestStatus = userRepository.getRequestStatus(5L, 6L);
    assertEquals("blocked", testRequestStatus);
  }

  @Test
  public void getRequestStatusReturnsBlockedReversed() {
    String testRequestStatus = userRepository.getRequestStatus(6L, 5L);
    assertEquals("blocked", testRequestStatus);
  }

  // getFriends Method Tests

  @Test
  public void getFriendsReturnsCorrectNumberOfFriends() {
    ArrayList<com.makersacademy.acebook.model.User> testList1 = new ArrayList<com.makersacademy.acebook.model.User>();
    Iterable<com.makersacademy.acebook.model.User> testFriends1 = userRepository.getFriends(Long.valueOf("7"));
    testFriends1.forEach(u -> testList1.add(u));
    assertEquals(2, testList1.size());
  }

  @Test
  public void getFriendsReturnsCorrectUserNames() {
    ArrayList<com.makersacademy.acebook.model.User> testList2 = new ArrayList<com.makersacademy.acebook.model.User>();
    Iterable<com.makersacademy.acebook.model.User> testFriends2 = userRepository.getFriends(Long.valueOf("1"));
    testFriends2.forEach(u -> testList2.add(u));
    assertEquals("Yasmin", testList2.get(0).getUsername());
    // assertEquals("Mario",testList.get(1).getUsername());
  }

  // getFriendRequests Method Tests

  @Test
  public void getFriendRequestsReturnsCorrectNumberOfRequests() {
    String testRequestStatus1 = userRepository.getRequestStatus(Long.valueOf("1L"), Long.valueOf("4"));
    assertEquals("accepted", testRequestStatus1);
  }

  @Test
  public void getFriendRequestsReturnsCorrectUserNames() {
    String testRequestStatus2 = userRepository.getRequestStatus(Long.valueOf("4L"), Long.valueOf("1"));
    assertEquals("accepted", testRequestStatus2);
  }
}
