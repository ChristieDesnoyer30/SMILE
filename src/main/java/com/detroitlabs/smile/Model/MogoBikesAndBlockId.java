package com.detroitlabs.smile.Model;

import javax.persistence.*;

@Entity
@Table(name="mogobikesandblockid")
public class MogoBikesAndBlockId {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name="city_block_id")
    private String cityBlockId;
    private String name;
    private String docks;
    private Double latitude;
    private Double longitude;

    public MogoBikesAndBlockId(String cityBlockId, String name, String docks, Double latitude, Double longitude) {
        this.cityBlockId = cityBlockId;
        this.name = name;
        this.docks = docks;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDocks() {
        return docks;
    }

    public void setDocks(String docks) {
        this.docks = docks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityBlockId() {
        return cityBlockId;
    }

    public void setCityBlockId(String cityBlockId) {
        this.cityBlockId = cityBlockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
