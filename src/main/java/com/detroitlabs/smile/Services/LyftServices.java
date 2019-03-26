package com.detroitlabs.smile.Services;
import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LyftServices {

    @Value("${LYFT_KEY}")
    private String lyftKey;

    public AllLyftData fetchLyftData(double lat, double lng){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + lyftKey);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllLyftData> response = restTemplate.exchange("https://api.lyft.com/v1/nearby-drivers-pickup-etas?lat="+lat + "&lng=" + lng
                , HttpMethod.GET, httpEntity, AllLyftData.class);

        return response.getBody();

    }
}
