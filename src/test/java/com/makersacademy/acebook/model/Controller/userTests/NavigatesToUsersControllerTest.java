package com.makersacademy.acebook.model.Controller.userTests;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.Assert.assertEquals;

public class NavigatesToUsersControllerTest {
    @Test
    public void testNavigatesToNewUsers() throws IOException {
        // URL to test
        String url = "http://localhost:8080/users/new";

        // Create a URL object
        URL obj = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        // Set the request method
        connection.setRequestMethod("GET");

        // Get the response code
        int responseCode = connection.getResponseCode();

        // Close the connection
        connection.disconnect();

        // Assert that the response code is 200 (OK)
        assertEquals(200, responseCode);
    }
}

