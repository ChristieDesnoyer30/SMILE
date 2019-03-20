package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LocationServices {

    public TopLocationInfo getLocationInfo(String address){
        RestTemplate rt = new RestTemplate();
        return rt.getForObject("https://geocoding.geo.census.gov/geocoder/geographies/onelineaddress?benchmark=4&vintage=4&format=json&address=" + address
                , TopLocationInfo.class);

    }

}
