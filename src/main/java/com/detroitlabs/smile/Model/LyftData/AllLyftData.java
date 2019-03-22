package com.detroitlabs.smile.Model.LyftData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllLyftData {

    private NearbyDriversPickUpEtas nearbyDriversPickUpEtas;

    @JsonProperty("nearby_drivers_pickup_etas")
    public NearbyDriversPickUpEtas getNearbyDriversPickUpEtas() {
        return nearbyDriversPickUpEtas;
    }
    @JsonProperty("nearby_drivers_pickup_etas")
    public void setNearbyDriversPickUpEtas(NearbyDriversPickUpEtas nearbyDriversPickUpEtas) {
        this.nearbyDriversPickUpEtas = nearbyDriversPickUpEtas;
    }
}
