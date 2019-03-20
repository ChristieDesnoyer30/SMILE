package com.detroitlabs.smile.Controller;

import com.detroitlabs.smile.Model.CrimeDataModel.CrimeData;
import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import com.detroitlabs.smile.Model.GeoDataModel.Coordinates;
import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import com.detroitlabs.smile.Services.CrimeServices;
import com.detroitlabs.smile.Services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LocationController {

    @Autowired
    LocationServices locationServices;

    @Autowired
    CrimeServices crimeServices;

    @RequestMapping("/")
    public String showPage() {
        return "index";
    }


    @RequestMapping("getAddress")
    public ModelAndView showHomePage(@RequestParam("address") String userInputAddress) {
        TopLocationInfo topLocationInfo = locationServices.getLocationInfo(userInputAddress);
        Coordinates coordinates = topLocationInfo.getResult().getAddressMatches().get(0).getCoordinates();
        String blockCode = topLocationInfo.getResult().getAddressMatches().get(0).getGeographies()
                .getCensusBlocks().get(0).getGeoID();
        TopCrimeData topCrimeData = crimeServices.getCrimeData(blockCode);

        ModelAndView mv = new ModelAndView("home");
        mv.addObject("coordinatesX", coordinates.getX());
        mv.addObject("coordinatesY", coordinates.getY());
        mv.addObject("geo", blockCode);
        mv.addObject("topcrime", topCrimeData);

        return mv;
    }



}
