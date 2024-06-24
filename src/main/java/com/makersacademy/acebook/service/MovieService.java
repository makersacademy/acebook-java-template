package com.makersacademy.acebook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.Movie;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class MovieService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletableFuture<List<Movie>> searchMovie(String query) {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        return client.prepare("GET", "https://moviedatabase8.p.rapidapi.com/Search/" + query)
                .setHeader("x-rapidapi-key", "f4de2aff69mshc8cba2b4117d03ep112cfcjsn5a204e5daac8")
                .setHeader("x-rapidapi-host", "moviedatabase8.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        JsonNode jsonNode = objectMapper.readTree(response.getResponseBody());
                        return objectMapper.convertValue(jsonNode, new TypeReference<List<Movie>>() {});
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to parse JSON", e);
                    }
                })
                .whenComplete((response, throwable) -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
