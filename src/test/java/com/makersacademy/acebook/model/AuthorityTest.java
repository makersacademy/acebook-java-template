package com.makersacademy.acebook.model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class AuthorityTest {
    private Authority authority = new Authority("John Daw", "ROLE_USER" );

    @Test
    public void postHasContent() {
        assertTrue(authority instanceof Authority);
    }
}
