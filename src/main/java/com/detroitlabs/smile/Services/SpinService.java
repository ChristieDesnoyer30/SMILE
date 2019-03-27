package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.SpinDataModel.AllSpinData;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SpinService {

//    @Value("${SPIN_JWT}")
//    private String spingJWT;

    public AllSpinData fetchSpinData(double lat, double lng){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "BEARER eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVW5pcXVlS2V5IjoiODNmMzhjMDM0NGVlOThhMTQ1ZDY2MzQ1YmZiM2RmYjAiLCJyZWZlcnJhbENvZGUiOm51bGwsImlzQXBwbGVQYXlEZWZhdWx0IjpmYWxzZSwiaXNBZG1pbiI6ZmFsc2UsImlzQ2hhcmdlciI6ZmFsc2UsImlzQ29ycG9yYXRlIjpmYWxzZSwiYXV0b1JlbG9hZCI6ZmFsc2UsImNyZWRpdEJhbGFuY2UiOjAsInRvdGFsVHJpcENvdW50IjowLCJzcGluVW5saW1pdGVkIjpmYWxzZSwic3BpblVubGltaXRlZE5leHRCaWxsaW5nQ3ljbGUiOm51bGwsInNwaW5VbmxpbWl0ZWRNZW1iZXJzaGlwIjpudWxsLCJpc1F1YWxpZmllZEZvclJpZGUiOmZhbHNlLCJyYXRlRGlzY291bnRQZXJjZW50YWdlIjowLCJ0eXBlIjoiZGV2aWNlIiwiZXhwIjoxNTUzNzE1MTc5fQ.LsaPoFRaRhQ2KjMpxL7NOHMMvFlTcmv2HuofmS42ZLU");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("User-Agent", "Spring" );
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllSpinData> response = restTemplate.exchange("https://web.spin.pm/api/v3/vehicles?lng="+ lng+ "&lat=" + lat,
                HttpMethod.GET, httpEntity, AllSpinData.class);

        return response.getBody();
    }

}
