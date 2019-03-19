package com.detroitlabs.smile.Model.GeoDataModel;

import com.detroitlabs.smile.Model.GeoDataModel.AddressMatches;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private AddressMatches addressMatches;

    @JsonProperty("addressMatches")
    public AddressMatches getAddressMatches() {
        return addressMatches;
    }

    @JsonProperty("addressMatches")
    public void setAddressMatches(AddressMatches addressMatches) {
        this.addressMatches = addressMatches;
    }
}
