package com.makersacademy.acebook.model;

        import static org.hamcrest.CoreMatchers.containsString;
        import static org.junit.Assert.*;

        import org.junit.Test;


public class UserTest {

    private User user = new User("James", " password", true, "https//test.com");

    @Test
    public void getUserName() {
        assertThat(user.getUsername(), containsString("James"));
    }
    @Test
    public void getUserPassword() {
        assertThat(user.getPassword(), containsString("password"));
    }
    @Test
    public void getUserImage() {
        assertThat(user.getuserimage(), containsString("https//test.com"));
    }

}
