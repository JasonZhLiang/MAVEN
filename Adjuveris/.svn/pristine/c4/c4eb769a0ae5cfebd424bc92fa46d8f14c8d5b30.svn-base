package com.linguaclassica.service;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeService { 
	/*
	//gets time now in other time zone stated in server time zone time
	public Timestamp getTimeNowInOtherTimeZone(String timezone){
		
		//LocalDateTime now = LocalDateTime.now();
		ZoneId sysId = ZoneId.systemDefault();
		ZoneId tzZoneId = getTimezone(timezone);
		ZonedDateTime nowZonedZDT = ZonedDateTime.ofInstant(Instant.now(), sysId);		
		Timestamp nowTS = new Timestamp(nowZonedZDT.toInstant().getEpochSecond() * 1000L);
		return nowTS;
	}
	*/
	//gets time now in server time stated in other time zone time
	public ZonedDateTime getOtherTimeZoneNow(String timezone){
		
		ZoneId tzZoneId = getTimezone(timezone);
        final ZonedDateTime now_there = ZonedDateTime.ofInstant(Instant.now(), tzZoneId);
		return now_there;		
	}
	
	//get specified time stated in timezone time
	public ZonedDateTime getTimeByTimeZone(Timestamp time, String timezone){
		
		ZoneId tzZoneId = getTimezone(timezone);
		LocalDateTime ldt = time.toLocalDateTime();
		ZonedDateTime zdt = ZonedDateTime.of(ldt, tzZoneId);

        ClassLoader cl = ClassLoader.getSystemClassLoader();

        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
        String classpth = System.getProperty("java.classpath");
        System.out.println("classpath = " + classpth);//shows null
		return zdt;
	}
	
	private ZoneId getTimezone(String timezone) {
		
		ZoneId tzZoneId = null;
		if(timezone.equals("ET") || timezone.equals("EST")){
			tzZoneId = ZoneId.of("America/New_York");
		}
		else if (timezone.equals("CT") || timezone.equals("CST")){
			tzZoneId = ZoneId.of("America/Chicago");
		}
		else if (timezone.equals("MT") || timezone.equals("MST")){
			tzZoneId = ZoneId.of("America/Denver");
		}
		else if (timezone.equals("PT") || timezone.equals("PST")){
			tzZoneId = ZoneId.of("America/Los_Angeles");
		}	
		return tzZoneId;
	}
}
