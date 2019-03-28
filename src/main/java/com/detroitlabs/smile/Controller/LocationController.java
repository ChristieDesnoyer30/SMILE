package com.detroitlabs.smile.Controller;

import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import com.detroitlabs.smile.Model.LocationAndCrimeZone;
import com.detroitlabs.smile.Model.LyftData.LocationInfo;
import com.detroitlabs.smile.Model.MogoBikesAndBlockId;
import com.detroitlabs.smile.Repository.LocationAndCrimeZoneRepository;
import com.detroitlabs.smile.Repository.MogoBikesRepository;
import com.detroitlabs.smile.Services.CrimeServices;
import com.detroitlabs.smile.Services.LocationServices;
import com.detroitlabs.smile.Services.LyftServices;
import com.detroitlabs.smile.Services.MogoBikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;


@Controller
public class LocationController {

    private final String NPF = "Non-Pedestrian Friendly";
    private final String PF = "Pedestrian Friendly";
    private final String SPF = "Semi-Pedestrian Friendly";

    @Value("${HERE_APP_ID}")
    String hereApiID;

    @Value("${HERE_APP_CODE}")
    String hereApiCode;

    @Autowired
    private LocationServices locationServices;

    @Autowired
    private CrimeServices crimeServices;

    @Autowired
    private LyftServices lyftServices;

    @Autowired
    private LocationAndCrimeZoneRepository locationAndCrimeZoneRepository;

    @Autowired
    MogoBikeService mogoBikeService;

    @Autowired
    MogoBikesRepository mogoBikesRepository;


    @RequestMapping("/")
    public String showPage() {
//        Do not uncomment out this method, was only needed once to populate our MogoBike database
//         mogoBikeService.createMogoBikeDataBaseWithCityBlockIds();

        return "index";
    }

    @RequestMapping("index.html")
    public String showResultsPage() {


        return "index";
    }
    @RequestMapping("safety-tips.html")
    public String showSafetyTips() {


        return "safety-tips";
    }

    @RequestMapping("getAddress")
    public ModelAndView showResultsPage(LocationAndCrimeZone locationAndCrimeZone, @RequestParam("startAddress") String startAddress, @RequestParam("address") String endAddress) {


        ModelAndView modelAndView = new ModelAndView();

        TopLocationInfo startLocationInfo = locationServices.getLocationInfo(startAddress);
        double startLat = locationServices.getLatitude(startLocationInfo);
        double startLng = locationServices.getLongtitude(startLocationInfo);


        ArrayList<LocationInfo> allLyftCoordinates = lyftServices.fetchLyftData(startLat, startLng).getNearbyDriversPickUpEtas().get(1).getNearby_drivers();


        String coordinates = " ";
        for (LocationInfo locationInfo : allLyftCoordinates) {
            String stringLatitude = Double.toString(locationInfo.getLocations().get(0).getLat());
            String stringLng = Double.toString(locationInfo.getLocations().get(0).getLng());
            coordinates += stringLatitude.concat(", " + stringLng + "||");
        }

        modelAndView.addObject("coordinates", coordinates);


        TopLocationInfo endLocationInfo = locationServices.getLocationInfo(endAddress);
        double endLat = locationServices.getLatitude(endLocationInfo);
        double endLng = locationServices.getLongtitude(endLocationInfo);

        locationAndCrimeZone.setLat(endLat);
        locationAndCrimeZone.setLng(endLng);

        String blockId = locationServices.getBlockID(endLocationInfo);
        locationAndCrimeZone.setBlockid(blockId);
        locationAndCrimeZone.setAddress(endAddress);

        if (endAddress.contains("DETROIT") || endAddress.contains("482")) {
            TopCrimeData highCrimeData = crimeServices.getHighCrimedata(blockId);
            TopCrimeData lowCrimeData = crimeServices.getLowCrimedata(blockId);
            if (highCrimeData.size() >= 2 || lowCrimeData.size() > 6) {
                locationAndCrimeZone.setCrimeZone(NPF);
                modelAndView.addObject("Zone", NPF);
            } else if ((highCrimeData.size() < 2 && highCrimeData.size() >= 1) || (lowCrimeData.size() >= 3 && lowCrimeData.size() <= 5)) {
                locationAndCrimeZone.setCrimeZone(SPF);
                modelAndView.addObject("Zone", SPF);

            } else {
                locationAndCrimeZone.setCrimeZone(PF);
                modelAndView.addObject("Zone", PF);
            }
            modelAndView.setViewName("choices");

            modelAndView.addObject("highCrime", highCrimeData);
            modelAndView.addObject("lowCrime", lowCrimeData);

        } else {
            modelAndView.setViewName("index");
        }


        MogoBikesAndBlockId mogoBikesAndBlockId = mogoBikesRepository.findByCityBlockId(blockId);

        if (mogoBikesAndBlockId != null) {
            modelAndView.addObject("bikelocation", "Bike Location: " + mogoBikesAndBlockId.getName());
            modelAndView.addObject("bikedock", "Number of docks in location: " + mogoBikesAndBlockId.getDocks());
        } else{
            modelAndView.addObject("bikelocation", "NO BIKES");
            modelAndView.addObject("bikedock", "AVAILABLE");
        }

        locationAndCrimeZoneRepository.save(locationAndCrimeZone);

        return modelAndView;
    }




}
