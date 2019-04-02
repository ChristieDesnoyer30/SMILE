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
    public void coordinateInfoForMapsShouldReturn() throws IOException {
        Vehicles vehicles = testSpinService.fetchSpinData(42.32833583,-83.03767604).getVehicles();
        Double[][] testArray =testSpinService.coordinateInfoForMaps(vehicles);
        System.out.println(testArray[0][1].toString());

        Assert.assertNotNull(testArray);

    }
}