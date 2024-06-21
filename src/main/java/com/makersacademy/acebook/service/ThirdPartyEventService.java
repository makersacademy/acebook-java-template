package com.makersacademy.acebook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.ThirdPartyEvent;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ThirdPartyEventService {

    private final ObjectMapper objectMapper = new ObjectMapper();

//    public CompletableFuture<List<ThirdPartyEvent>> searchEvent(String query) {
//        AsyncHttpClient client = new DefaultAsyncHttpClient();
//        return client.prepare("GET", "https://moviedatabase8.p.rapidapi.com/Search/" + query)
//                .setHeader("x-rapidapi-key", "{key}")
//                .setHeader("x-rapidapi-host", "moviedatabase8.p.rapidapi.com")
//                .execute()
//                .toCompletableFuture()
//                .thenApply(response -> {
//                    try {
//                        JsonNode jsonNode = objectMapper.readTree(response.getResponseBody());
//                        return objectMapper.convertValue(jsonNode, new TypeReference<List<ThirdPartyEvent>>() {});
//                    } catch (Exception e) {
//                        throw new RuntimeException("Failed to parse JSON", e);
//                    }
//                })
//                .whenComplete((response, throwable) -> {
//                    try {
//                        client.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });

    public CompletableFuture<List<ThirdPartyEvent>> searchEvent() {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        return client.prepare("GET", "https://real-time-events-search.p.rapidapi.com/search-events?query=Newcastle%2C%20UK&start=0")
                .setHeader("x-rapidapi-key", "330a9e01edmsh3544e9d43e6ff69p17ee8ejsnfee0e591ba76")
                .setHeader("x-rapidapi-host", "real-time-events-search.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    try {
                        JsonNode rootNode = objectMapper.readTree(response.getResponseBody());
                        JsonNode innerNode = rootNode.get("data");
                        return objectMapper.convertValue(innerNode, new TypeReference<List<ThirdPartyEvent>>() {});
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
