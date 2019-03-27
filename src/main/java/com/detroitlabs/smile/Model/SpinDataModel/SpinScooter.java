package com.detroitlabs.smile.Model.SpinDataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SpinScooter {

    private double lat;
    private double lng;
    private String last4;
    private int battPercentage;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    @JsonProperty("batt_percentage")
    public int getBattPercentage() {
        return battPercentage;
    }

    @JsonProperty("batt_percentage")
    public void setBattPercentage(int battPercentage) {
        this.battPercentage = battPercentage;
    }
}
