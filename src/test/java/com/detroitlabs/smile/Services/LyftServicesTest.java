package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.LyftData.AllLyftData;
import com.detroitlabs.smile.Model.LyftData.LocationInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;


@RunWith(SpringJUnit4ClassRunner.class)
public class LyftServicesTest {

    @Value("${LYFT_KEY}")
    private String key;

    @Test
    public void shouldFetchLyftDataAndCheckArraySizeOf6() {
        LyftServices lyftServices = new LyftServices();
        lyftServices.setLyftKey(key);

        ArrayList<LocationInfo> lyftTestArray = lyftServices.fetchLyftData(42.402580,-82.937760).getNearbyDriversPickUpEtas().get(1).getNearby_drivers();
        Assert.assertNotNull(lyftTestArray);
    }


}