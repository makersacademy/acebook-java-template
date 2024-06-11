package com.makersacademy.acebook.model.Controller.userTests;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class NavigatesToLoginAfterUsersControllerTest {
    @Test
    public void testNavigatesToLoginAfterUsers() throws IOException {
        // URL to test
        String url = "http://localhost:8080/users/new";

        // Create a URL object
        URL obj = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // Set the request method
        connection.setRequestMethod("GET");

        // Enable following redirects
        connection.setInstanceFollowRedirects(false);

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Get the redirection URL
        String redirectURL = connection.getHeaderField("Location");

        // Close the connection
        connection.disconnect();

        // Assert that the response code is 302 (Found) indicating a redirect
        assertEquals(200, responseCode);

        // Assert that the redirection URL is the login page
        assertEquals("http://localhost:8080/login", redirectURL);
    }
}
