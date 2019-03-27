package com.detroitlabs.smile.Services;

import com.detroitlabs.smile.Model.GeoCodingMogoApiModel.AllGeoData;
import com.detroitlabs.smile.Model.MogoBikeApiModel.AllBikeData;
import com.detroitlabs.smile.Model.MogoBikeApiModel.BikeInfo;
import com.detroitlabs.smile.Model.MogoBikesAndBlockId;
import com.detroitlabs.smile.Repository.MogoBikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class MogoBikeService {

    @Autowired
    MogoBikesRepository mogoBikeRepo;

    public AllBikeData fetchMogoBikeInfo(){

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("https://data.detroitmi.gov/resource/2w3e-8kx8.json", AllBikeData.class);
    }

    public AllGeoData fetchCityBlockCodes(double latitude, double longitude){
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("https://geo.fcc.gov/api/census/block/find?latitude=" + latitude +
                        "&longitude="+ longitude +"&format=json",
                AllGeoData.class);
    }


    public void createMogoBikeDataBaseWithCityBlockIds(){

        AllBikeData allBikeData = fetchMogoBikeInfo();

        for(BikeInfo bikes : allBikeData){
            double longitude = bikes.getLocation().getCoordinates().get(0);
            double latitude = bikes.getLocation().getCoordinates().get(1);
            String name = bikes.getName();
            String docks = bikes.getDocks();

            AllGeoData allGeoData = fetchCityBlockCodes(latitude,longitude);

            String blockId = allGeoData.getBlock().getFips();

            MogoBikesAndBlockId mogoBikesAndBlockId =  new MogoBikesAndBlockId(blockId, name, docks, latitude, longitude);

            mogoBikeRepo.save(mogoBikesAndBlockId);

        }

    }

}
