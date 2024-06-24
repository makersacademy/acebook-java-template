package com.makersacademy.acebook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makersacademy.acebook.model.ThirdPartyEvent;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ThirdPartyEventService {

    @Value("${API_KEY}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CompletableFuture<List<ThirdPartyEvent>> getDefaultTPEvents() {

        AsyncHttpClient client = new DefaultAsyncHttpClient();

        return client.prepare("GET", "https://real-time-events-search.p.rapidapi.com/search-events?query=Newcastle%2C%20UK&is_virtual=false&start=0" )
                .setHeader("x-rapidapi-key", apiKey)
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

    public CompletableFuture<List<ThirdPartyEvent>> searchTPEvents(Date minScheduledDate, Date maxScheduledDate) {

        AsyncHttpClient client = new DefaultAsyncHttpClient();
        System.out.println("\n!!!!!!!!!!!!!!!!!!!!");
        System.out.println(" IN SEARCH TPEVENTS ");
        System.out.println("!!!!!!!!!!!!!!!!!!!!\n");

        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String stringMinScheduledDate = df.format(minScheduledDate);
        String stringMaxScheduledDate = df.format(maxScheduledDate);
        String URL = "https://real-time-events-search.p.rapidapi.com/search-events?query=Newcastle%2CUK%20" + stringMinScheduledDate + "%20to%20" + stringMaxScheduledDate + "&date=any&is_virtual=false&start=0";
        System.out.println("\n!!!!!!!!!!!!!!!!!!!!");
        System.out.println(URL);
        System.out.println("!!!!!!!!!!!!!!!!!!!!\n");

        return client.prepare("GET", "https://real-time-events-search.p.rapidapi.com/search-events?query=Newcastle%2CUK%20" + stringMinScheduledDate + "%20to%20" + stringMaxScheduledDate + "&date=any&is_virtual=false&start=0")
                .setHeader("x-rapidapi-key", apiKey)
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
