package com.detroitlabs.smile.Model.GeoDataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoData {

    private String geoID;

    @JsonProperty("GEOID")
    public String getGeoID() {
        return geoID;
    }

    @JsonProperty("GEOID")
    public void setGeoID(String geoID) {
        this.geoID = geoID;
    }
}
