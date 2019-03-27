package com.detroitlabs.smile.Model.MogoBikeApiModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BikeInfo {
    private String name;
    private String docks;
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocks() {
        return docks;
    }

    public void setDocks(String docks) {
        this.docks = docks;
    }

}
