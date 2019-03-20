package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CrimeServices {
    public TopCrimeData getCrimeData(String blockID){
        RestTemplate rt = new RestTemplate();
        return rt.getForObject("https://data.detroitmi.gov/resource/6gdg-y3kf.json?block_id=" + blockID
                ,TopCrimeData.class);

    }
}
