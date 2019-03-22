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

    @RequestMapping("index.html")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("getAddress")
    public ModelAndView showHomePage(@RequestParam("address") String userInputAddress) {
        ModelAndView modelAndView = new ModelAndView();
        if (userInputAddress.contains("DETROIT") || userInputAddress.contains("482")) {
            TopLocationInfo topLocationInfo = locationServices.getLocationInfo(userInputAddress);
            String blockId = topLocationInfo.getResult().getAddressMatches().get(0).getGeographies()
                    .getCensusBlocks().get(0).getGeoID();
            TopCrimeData highCrimeData = crimeServices.getHighCrimedata(blockId);
            TopCrimeData lowCrimeData = crimeServices.getLowCrimedata(blockId);
            if (highCrimeData.size() >= 2 || lowCrimeData.size() > 6) {
                modelAndView.addObject("Zone", "NPF");
            } else if ((highCrimeData.size() < 2 && highCrimeData.size()>=1) || (lowCrimeData.size() >= 3 && lowCrimeData.size() <= 5)) {
                modelAndView.addObject("Zone", "SPF");

            } else {
                modelAndView.addObject("Zone", "PF");
            }
            modelAndView.setViewName("smileresults");

            modelAndView.addObject("highCrime", highCrimeData);
            modelAndView.addObject("lowCrime", lowCrimeData);

        } else {
            modelAndView.setViewName("index");
        }


        return modelAndView;
    }


}
