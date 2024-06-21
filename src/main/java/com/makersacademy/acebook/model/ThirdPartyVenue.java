package com.makersacademy.acebook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)

@Data

public class ThirdPartyVenue {
    private String full_address;
}
