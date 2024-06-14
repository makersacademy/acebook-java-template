package com.makersacademy.acebook.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class FriendTest {

    private User sender;
    private User recipient;
    private Friend friend;

    @Before
    public void setUp() {
        sender = mock(User.class);
        recipient = mock(User.class);
        friend = new Friend(sender, recipient);

    }
    @Test
    public void friendHasSender() {
        assertEquals(sender, friend.getSender());
    }

    @Test
    public void friendHasRecipient() {
        assertEquals(recipient, friend.getRecipient());
    }
    @Test
    public void friendIsInitiallyNotAccepted() {
        assertFalse(friend.isAccepted());
    }
    @Test
    public void friendCanBeAccepted() {
        friend.setAccepted(true);
        assertTrue(friend.isAccepted());
    }
    @Test
    public void friendHasTimestamp() {
        Timestamp timestamp = new Timestamp(10000);
        friend.setCreatedAt(timestamp);
        assertEquals(timestamp, friend.getCreatedAt());
    }

}
