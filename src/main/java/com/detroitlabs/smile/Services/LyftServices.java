package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LyftServices {
    public AllLyftData fetchLyftData(Double lat, Double lng){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER hy9zClCgFkm1YrZsy6MN6RF8suIVk88Pkx2vWLaeUkRxZx+hw+gcRyo9mj8uVvwRe1zd2qOXTR8QsYWCy3sFY1iHg0ZPwRAMsS47eiUpgcNnYoU7e1vKfMI=");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllLyftData> response = restTemplate.exchange("https://api.lyft.com/v1/nearby-drivers-pickup-etas?lat="+lat + "&lng=" + lng
                , HttpMethod.GET, httpEntity, AllLyftData.class);

        return response.getBody();

    }
}
