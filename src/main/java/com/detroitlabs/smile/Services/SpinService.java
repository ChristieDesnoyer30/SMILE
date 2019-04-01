package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.SpinDataModel.AllSpinData;

import com.detroitlabs.smile.Model.SpinDataModel.SpinScooter;
import com.detroitlabs.smile.Model.SpinDataModel.Vehicles;
import com.detroitlabs.smile.Model.Token.Token;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Component
public class SpinService {



    public Token generateJWT(){

        String json = "{\"device\":{\"mobileType\":\"ios\",\"uid\":\"e0314af1-6470-43a8-a554-42fd0ac8b6d5\"},\"grantType\":\"device\"}\r\n";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("User-Agent", "Spring");

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        Token answer = restTemplate.postForObject("https://web.spin.pm/api/v1/auth_tokens",entity, Token.class);

        return answer;

    }



    public AllSpinData fetchSpinData(double lat, double lng) throws IOException {

        String jwtToken = generateJWT().getJwt();

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER " + jwtToken);
        headers.add("Accept",org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
        headers.add("User-Agent", "Spring" );
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllSpinData> response = restTemplate.exchange("https://web.spin.pm/api/v3/vehicles?lng="+ lng+ "&lat=" + lat,
                HttpMethod.GET, httpEntity, AllSpinData.class);

        return response.getBody();
    }


    public String coordinateInfoForMaps(Vehicles vehicles){
        String coordinates= " ";

        for (SpinScooter spinScooter: vehicles) {
            String stringLatitude = Double.toString(spinScooter.getLat());
            String stringLng = Double.toString(spinScooter.getLng());
            coordinates += stringLatitude.concat(", " + stringLng + "||");
        }

        return coordinates;
    }

}
