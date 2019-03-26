package com.detroitlabs.smile.Controller;

import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import com.detroitlabs.smile.Model.LocationAndCrimeZone;
import com.detroitlabs.smile.Model.LyftData.LocationInfo;
import com.detroitlabs.smile.Repository.LocationAndCrimeZoneRepository;
import com.detroitlabs.smile.Services.CrimeServices;
import com.detroitlabs.smile.Services.LocationServices;
import com.detroitlabs.smile.Services.LyftServices;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LocationServices locationServices;

    @Autowired
    private CrimeServices crimeServices;

    @Autowired
    private LyftServices lyftServices;

    @Autowired
    private LocationAndCrimeZoneRepository locationAndCrimeZoneRepository;

    @RequestMapping("/")
    public String showPage() {
        return "index";
    }

    @RequestMapping("index.html")
    public String showResultsPage() {
        return "index";
    }

    @RequestMapping("getAddress")
    public ModelAndView showResultsPage(@RequestParam("address") String userInputAddress) {
        ModelAndView modelAndView = new ModelAndView();
        LocationAndCrimeZone locationAndCrimeZone = new LocationAndCrimeZone();

        TopLocationInfo topLocationInfo = locationServices.getLocationInfo(userInputAddress);
        String blockId = topLocationInfo.getResult().getAddressMatches().get(0).getGeographies()
                .getCensusBlocks().get(0).getGeoID();
        locationAndCrimeZone.setBlockid(blockId);
        locationAndCrimeZone.setAddress(userInputAddress);

        if (userInputAddress.contains("DETROIT") || userInputAddress.contains("482")) {
            TopCrimeData highCrimeData = crimeServices.getHighCrimedata(blockId);
            TopCrimeData lowCrimeData = crimeServices.getLowCrimedata(blockId);
            if (highCrimeData.size() >= 2 || lowCrimeData.size() > 6) {
                locationAndCrimeZone.setCrimeZone(NPF);
                modelAndView.addObject("Zone", NPF);
            } else if ((highCrimeData.size() < 2 && highCrimeData.size()>=1) || (lowCrimeData.size() >= 3 && lowCrimeData.size() <= 5)) {
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

        double lat = topLocationInfo.getResult().getAddressMatches().get(0).getCoordinates().getY();
        System.out.println(lat);
        double lng = topLocationInfo.getResult().getAddressMatches().get(0).getCoordinates().getX();
        System.out.println(lng);

        ArrayList<LocationInfo> allLyftCoordinates = lyftServices.fetchLyftData(lat,lng).getNearbyDriversPickUpEtas().get(1).getNearby_drivers();
        modelAndView.addObject("nearbyDrivers",allLyftCoordinates);

        locationAndCrimeZone.setLat(lat);
        locationAndCrimeZone.setLng(lng);

        locationAndCrimeZoneRepository.save(locationAndCrimeZone);

        return modelAndView;
    }


}
