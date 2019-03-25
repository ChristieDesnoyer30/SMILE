package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LyftServices {
    public AllLyftData fetchLyftData(double lat, double lng){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER cp3BhWQ/5cI6dp9G83u6UoapBYnJ8GWoDM1Mzc76HpB1A3efCSezSnRhToIQd0yDjbusd5cnDvVZSF8C/XdOe+kVZEGf3CCG3bNg3kvTUehgRquEdtFb0kQ=");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllLyftData> response = restTemplate.exchange("https://api.lyft.com/v1/nearby-drivers-pickup-etas?lat="+lat + "&lng=" + lng
                , HttpMethod.GET, httpEntity, AllLyftData.class);

        return response.getBody();

    }
}
