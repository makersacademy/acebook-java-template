package com.makersacademy.acebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class ThirdPartyEvent {
//    private String event_id;
//    private String event_mid;
//    private String name;
//    private String link;
//    private String description;
//    private String start_time;
//    private String start_time_utc;
//    private Long start_time_precision_sec;
//    private String end_time;
//    private String end_time_utc;
//    private Long end_time_precision_sec;
//    private boolean is_virtual;
//    private String thumbnail;
//    private String publisher;
//    private String publisher_favicon;
//    private String publisher_domain;
//    private HashMap<Long, HashMap<String, String>> ticket_links;
//    private HashMap<Long, HashMap<String, String>> info_links;
//    private String venue;
//    private String tags;
//    private String language;

    private String event_id;
    private String name;
    private String link;
    private String description;
    private String start_time;
    private String end_time;
    private ThirdPartyVenue venue;
}
