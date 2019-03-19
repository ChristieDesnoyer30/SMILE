package com.detroitlabs.smile.Controller;

import com.detroitlabs.smile.Model.GeoDataModel.Coordinates;
import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import com.detroitlabs.smile.Services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LocationController {

    @Autowired
    LocationServices locationServices;

    @RequestMapping("/")
    public ModelAndView showHomePage(){
        TopLocationInfo topLocationInfo = locationServices.getLocationInfo();
        Coordinates coordinates = topLocationInfo.getResult().getAddressMatches().get(0).getCoordinates();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("coordinatesX", coordinates.getX());
        mv.addObject("coordinatesY", coordinates.getY());

       return mv;
    }

//    @ResponseBody
//    @RequestMapping("/")
//    public String displayLocationInfo(){
//        return "hello";
//    }

}
