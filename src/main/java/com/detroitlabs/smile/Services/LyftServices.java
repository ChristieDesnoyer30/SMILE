package com.detroitlabs.smile.Services;
import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import com.detroitlabs.smile.Model.LyftData.LocationInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class LyftServices {

    @Value("${LYFT_KEY}")
    private String lyftKey;

    public void setLyftKey(String lyftKey) {
        this.lyftKey = lyftKey;
    }

    public AllLyftData fetchLyftData(double lat, double lng){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + lyftKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllLyftData> response = restTemplate.exchange("https://api.lyft.com/v1/nearby-drivers-pickup-etas?lat="+ lat + "&lng=" + lng
                , HttpMethod.GET, httpEntity, AllLyftData.class);

        return response.getBody();

    }


    public Double[][] lyftCoordinateInfoForMaps(ArrayList<LocationInfo> allLocationInfo){

        Double[][] lyftCoordsForMap = new Double[allLocationInfo.size()][];

        for (int i =0; i< allLocationInfo.size(); i++) {
            Double[] latAndLong = new Double[2];

            latAndLong[0] = allLocationInfo.get(i).getLocations().get(0).getLat();
            latAndLong[1] = allLocationInfo.get(i).getLocations().get(0).getLng();

            lyftCoordsForMap[i] = latAndLong;
        }

        return lyftCoordsForMap;
    }
}
