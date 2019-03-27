package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class LocationServicesTest {

    @Test
    public void shouldReturn200StatusCode(){

        RestTemplate rt = new RestTemplate();

        ResponseEntity<TopLocationInfo> results = rt.getForEntity("https://geocoding.geo.census.gov/geocoder/geographies/onelineaddress?benchmark=4&vintage=4&format=json&address=4895 Audubon Rd Detroit MI"
                , TopLocationInfo.class);

        Assert.assertEquals(200, results.getStatusCodeValue());

    }

}