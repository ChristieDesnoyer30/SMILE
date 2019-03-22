package com.detroitlabs.smile.Model.LyftData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriverInfo {

    private Nearby_Drivers nearby_drivers;

    @JsonProperty("nearby_drivers")
    public Nearby_Drivers getNearby_drivers() {
        return nearby_drivers;
    }

    @JsonProperty("nearby_drivers")
    public void setNearby_drivers(Nearby_Drivers nearby_drivers) {
        this.nearby_drivers = nearby_drivers;
    }
}
