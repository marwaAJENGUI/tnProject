package tn.esprit.spring.sevice.impl;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoIPTestController {
	
    private RawDBDemoGeoIPLocationService locationService;

    public GeoIPTestController() throws IOException {
        locationService = new RawDBDemoGeoIPLocationService();
    }

    @PostMapping("/GeoIPTest/{ipAddress}")
	@ResponseBody
    public GeoIP getLocation(@RequestParam(value="ipAddress", required=true) String ipAddress) throws Exception {

//        GeoIPLocationService<String, GeoIP> locationService = new RawDBDemoGeoIPLocationService();
//        return locationService.getLocation(ipAddress);
    	   return locationService.getLocation(ipAddress);
    }
}

