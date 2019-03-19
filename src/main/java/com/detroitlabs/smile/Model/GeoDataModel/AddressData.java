package com.detroitlabs.smile.Model.GeoDataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressData {
    //also will hold coordinates object

    private Coordinates coordinates;

    private Geographies geographies;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Geographies getGeographies() {
        return geographies;
    }

    public void setGeographies(Geographies geographies) {
        this.geographies = geographies;
    }
}
