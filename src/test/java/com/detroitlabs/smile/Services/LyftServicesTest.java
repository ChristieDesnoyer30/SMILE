package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

public class LyftServicesTest {


    @Test
    public void shouldFetchYelpDataAndCheckArraySizeOf6() {

        LyftServices lyftServices = new LyftServices();

        int lyftArraySize = lyftServices.fetchLyftData().getNearbyDriversPickUpEtas().size();


        Assert.assertEquals(6, lyftArraySize);

    }
}