package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.SpinDataModel.AllSpinData;
import com.detroitlabs.smile.Model.SpinDataModel.Vehicles;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


public class SpinServiceTest {

    private SpinService testSpinService;

    @Before
    public void create(){
        testSpinService = new SpinService();
    }


    @Test
    public void shouldfetchSpinDataAndReturnArrayOfVehicles() throws IOException {

        AllSpinData spinData = testSpinService.fetchSpinData(42.332958,-83.047857);
        Assert.assertNotNull(spinData);

    }

    @Test
    public void shouldReturn200ResponseCode(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyVW5pcXVlS2V5IjoiODNmMzhjMDM0NGVlOThhMTQ1ZDY2MzQ1YmZiM2RmYjAiLCJyZWZlcnJhbENvZGUiOm51bGwsImlzQXBwbGVQYXlEZWZhdWx0IjpmYWxzZSwiaXNBZG1pbiI6ZmFsc2UsImlzQ2hhcmdlciI6ZmFsc2UsImlzQ29ycG9yYXRlIjpmYWxzZSwiYXV0b1JlbG9hZCI6ZmFsc2UsImNyZWRpdEJhbGFuY2UiOjAsInRvdGFsVHJpcENvdW50IjowLCJzcGluVW5saW1pdGVkIjpmYWxzZSwic3BpblVubGltaXRlZE5leHRCaWxsaW5nQ3ljbGUiOm51bGwsInNwaW5VbmxpbWl0ZWRNZW1iZXJzaGlwIjpudWxsLCJpc1F1YWxpZmllZEZvclJpZGUiOmZhbHNlLCJyYXRlRGlzY291bnRQZXJjZW50YWdlIjowLCJ0eXBlIjoiZGV2aWNlIiwiZXhwIjoxNTUzNzE1MTc5fQ.LsaPoFRaRhQ2KjMpxL7NOHMMvFlTcmv2HuofmS42ZLU");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("User-Agent", "Spring" );
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<AllSpinData> response = restTemplate.exchange("https://web.spin.pm/api/v3/vehicles?lng=-83.047857&lat=42.332958",
                HttpMethod.GET, httpEntity, AllSpinData.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void coordinateInfoForMapsShouldReturn() throws IOException {
        Vehicles vehicles = testSpinService.fetchSpinData(42.32833583,-83.03767604).getVehicles();
        Double[][] testArray =testSpinService.coordinateInfoForMaps(vehicles);
        System.out.println(testArray[0][1].toString());

        Assert.assertNotNull(testArray);





    }
}