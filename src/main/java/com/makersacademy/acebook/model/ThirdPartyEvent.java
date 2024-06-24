package com.makersacademy.acebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class ThirdPartyEvent {

    @JsonProperty("event_id")
    private String eventId;
    private String name;
    private String link;
    private String description;

    @JsonProperty("start_time")
    private String startTime;
    @JsonProperty("end_time")
    private String endTime;
    private ThirdPartyVenue venue;
    private String thumbnail;


    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public ThirdPartyEvent () {
    }

    public String getFormattedStartTime() {
        return formatDateTime(startTime);
    }

    public String getFormattedEndTime() {
        return formatDateTime(endTime);
    }

    private String formatDateTime(String dateTime) {
        if (dateTime != null && !dateTime.isEmpty()) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTime, INPUT_FORMATTER);
            return localDateTime.format(OUTPUT_FORMATTER);
        }
        return null;
    }
}