package com.makersacademy.acebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
public class Movie {
    private String id;
    private String title;
    private double vote_average;
    private int vote_count;
    private String status;
    private String release_date;
    private long revenue;
    private int runtime;
    private boolean adult;
    private String backdrop_path;
    private long budget;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String tagline;
    private String genres;
    private String production_companies;
    private String production_countries;
    private String spoken_languages;
}
