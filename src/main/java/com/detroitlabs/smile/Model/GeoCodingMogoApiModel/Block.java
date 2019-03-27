package com.detroitlabs.smile.Model.GeoCodingMogoApiModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block {
    private String fips;

    @JsonProperty("FIPS")
    public String getFips() {
        return fips;
    }

    @JsonProperty("FIPS")
    public void setFips(String fips) {
        this.fips = fips;
    }
}
