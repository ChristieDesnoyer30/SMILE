package com.detroitlabs.smile.Services;

import org.junit.Assert;
import org.junit.Test;

public class LyftServicesTest {


    @Test
    public void shouldFetchLyftDataAndCheckArraySizeOf6() {

        LyftServices lyftServices = new LyftServices();

        int lyftArraySize = lyftServices.fetchLyftData(42.402580,-82.937760).getNearbyDriversPickUpEtas().size();


        Assert.assertEquals(5, lyftArraySize);

    }
}