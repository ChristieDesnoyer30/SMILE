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
    private String offenseCategory;
    private String year;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

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

    @JsonProperty("offense_category")
    public String getOffenseCategory() {
        return offenseCategory;
    }

    @JsonProperty("offense_category")
    public void setOffenseCategory(String offenseCategory) {
        this.offenseCategory = offenseCategory;
    }

    public String removedTFromDateAndTime(){
        return incidentTimeStamp.replace("T", " ");
    }


    public String formattedDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        Date date = simpleDateFormat.parse(removedTFromDateAndTime());
        String[] date1 = date.toString().split(" ");

        return date1[0] +" "+ date1[1] +" " +date1[2] + " " + date1[3]+ " " + date1[4];

    }

}

