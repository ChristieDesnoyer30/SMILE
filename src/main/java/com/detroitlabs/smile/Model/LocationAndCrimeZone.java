package com.detroitlabs.smile.Model;


import javax.persistence.*;


@Entity
@Table(name="locationandcrimezone")
public class LocationAndCrimeZone {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String blockid;
    private String address;

    @Column(name="crimezone")
    private String crimeZone;
    private Double lat;
    private Double lng;

    public LocationAndCrimeZone() {
    }

    public LocationAndCrimeZone(String blockid, String address, String crimeZone, Double lat, Double lng) {
        this.blockid = blockid;
        this.address = address;
        this.crimeZone = crimeZone;
        this.lat = lat;
        this.lng = lng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockid() {
        return blockid;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrimeZone() {
        return crimeZone;
    }

    public void setCrimeZone(String crimeZone) {
        this.crimeZone = crimeZone;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
