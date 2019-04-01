package com.detroitlabs.smile.Controller;

import com.detroitlabs.smile.Model.CrimeDataModel.TopCrimeData;
import com.detroitlabs.smile.Model.EmailForm;
import com.detroitlabs.smile.Model.GeoDataModel.TopLocationInfo;
import com.detroitlabs.smile.Model.LocationAndCrimeZone;
import com.detroitlabs.smile.Model.LyftData.LocationInfo;
import com.detroitlabs.smile.Model.MogoBikesAndBlockId;
import com.detroitlabs.smile.Repository.EmailFormRepository;
import com.detroitlabs.smile.Model.SpinDataModel.AllSpinData;
import com.detroitlabs.smile.Repository.LocationAndCrimeZoneRepository;
import com.detroitlabs.smile.Repository.MogoBikesRepository;
import com.detroitlabs.smile.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
    private MogoBikeService mogoBikeService;

    @Autowired
    private MogoBikesRepository mogoBikesRepository;

    @Autowired
    private EmailFormRepository emailFormRepository;

    @Autowired
    SpinService spinService;


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
    public ModelAndView showResultsPage(LocationAndCrimeZone locationAndCrimeZone, @RequestParam("startAddress") String startAddress, @RequestParam("address") String endAddress, RedirectAttributes redirectAttrs) throws IOException {
        ModelAndView modelAndView = new ModelAndView();

        if (endAddress.contains("DETROIT") || endAddress.contains("482") && startAddress.contains("DETROIT") || startAddress.contains("482")) {
            TopLocationInfo endLocationInfo = locationServices.getLocationInfo(endAddress);
            double endLat = locationServices.getLatitude(endLocationInfo);
            double endLng = locationServices.getLongtitude(endLocationInfo);

            locationAndCrimeZone.setLat(endLat);
            locationAndCrimeZone.setLng(endLng);


            String blockId = locationServices.getBlockID(endLocationInfo);
            locationAndCrimeZone.setBlockid(blockId);
            locationAndCrimeZone.setAddress(endAddress);
            TopCrimeData highCrimeData = crimeServices.getHighCrimedata(blockId);
            TopCrimeData lowCrimeData = crimeServices.getLowCrimedata(blockId);
            modelAndView.addObject("blockID", blockId);


            if (highCrimeData.size() >= 2 || lowCrimeData.size() > 6) {
                locationAndCrimeZone.setCrimeZone(NPF);
                modelAndView.addObject("Zone",NPF);
            } else if ((highCrimeData.size() < 2 && highCrimeData.size() >= 1) || (lowCrimeData.size() >= 3 && lowCrimeData.size() <= 5)) {
                locationAndCrimeZone.setCrimeZone(SPF);
                modelAndView.addObject("Zone",SPF);

            } else {
                locationAndCrimeZone.setCrimeZone(PF);
                modelAndView.addObject("Zone",PF);
            }


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
            modelAndView.setViewName("choices");

            modelAndView.addObject("highCrime",highCrimeData);
            modelAndView.addObject("lowCrime",lowCrimeData);

            MogoBikesAndBlockId mogoBikesAndBlockId = mogoBikesRepository.findByCityBlockId(blockId);

            if (mogoBikesAndBlockId != null) {
                modelAndView.addObject("bikelocation", "Bike Location: " + mogoBikesAndBlockId.getName());
                modelAndView.addObject("bikedock", "Number of docks in location: " + mogoBikesAndBlockId.getDocks());
            } else {
                modelAndView.addObject("bikelocation", "NO BIKES");
                modelAndView.addObject("bikedock", "AVAILABLE");
            }

            locationAndCrimeZoneRepository.save(locationAndCrimeZone);

            AllSpinData allSpinData = spinService.fetchSpinData(startLat, startLng);

            modelAndView.addObject("vehicles", allSpinData.getVehicles());


        } else {

            modelAndView = new ModelAndView("redirect:/");
            redirectAttrs.addFlashAttribute("message", "Must enter an address with DETROIT or a 482.. zip code");
        }


        return modelAndView;
    }

    @RequestMapping("getEmail")
    public ModelAndView showEmailResult(@RequestParam("blockidHidden") String blockID,@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("category") String category,@RequestParam("message") String message) {

        ModelAndView modelAndView = new ModelAndView();

        EmailForm emailForm = new EmailForm(blockID, name, email, category, message);

        modelAndView.addObject("sentEmail", emailForm);
        modelAndView.setViewName("choices");

        emailFormRepository.save(emailForm);
        return modelAndView;

    }
}
