package org.kanomchan.core.common.test;

import java.io.IOException;

import com.google.gson.Gson;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import com.maxmind.geoip.timeZone;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			LookupService lookupService = new LookupService("/GeoLiteCity.dat",LookupService.GEOIP_MEMORY_CACHE );
			Location location = lookupService.getLocation("127.0.0.1");
			 System.out.println(" countryCode: " + location.countryCode +
                   "\n countryName: " + location.countryName +
                   "\n region: " + location.region +
                   "\n regionName: " + regionName.regionNameByCode(location.countryCode, location.region) +
                   "\n city: " + location.city +
                   "\n postalCode: " + location.postalCode +
                   "\n latitude: " + location.latitude +
                   "\n longitude: " + location.longitude +
//                   "\n distance: " + location.distance(l1) +
//                   "\n distance: " + l1.distance(location) +
	       "\n metro code: " + location.metro_code +
	       "\n area code: " + location.area_code +
                   "\n timezone: " + timeZone.timeZoneByCountryAndRegion(location.countryCode, location.region));
			 
			 Gson gson = new Gson();
			 
			 System.out.println(gson.toJson(location));
			 lookupService.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
