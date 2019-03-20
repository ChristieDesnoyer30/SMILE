package com.detroitlabs.smile.Model.CrimeDataModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CrimeData {

    private String address;
    private String chargeDescription;
    private String offenseDescription;
    private String incidentTimeStamp;
    private String neighborhood;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("charge_description")
    public String getChargeDescription() {
        return chargeDescription;
    }

    @JsonProperty("charge_description")
    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    @JsonProperty("offense_description")
    public String getOffenseDescription() {
        return offenseDescription;
    }
    @JsonProperty("offense_description")
    public void setOffenseDescription(String offenseDescription) {
        this.offenseDescription = offenseDescription;
    }
    @JsonProperty("incident_timestamp")
    public String getIncidentTimeStamp() {
        return incidentTimeStamp;
    }
    @JsonProperty("incident_timestamp")
    public void setIncidentTimeStamp(String incidentTimeStamp) {
        this.incidentTimeStamp = incidentTimeStamp;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String removedTfromDateAndTime(){
        return incidentTimeStamp.replace("T", " ");
    }

    public Date formattedDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        Date date = simpleDateFormat.parse(removedTfromDateAndTime());
        return date;

    }
}

