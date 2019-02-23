package com.linguaclassica.service;

import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	static final List<String> timeZone = Arrays.asList(new String[] {"ET", "CT","MT","PT"});
		
	private SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
	


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
	
	public ZoneId getTimezone(String timezone) {
		
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
	
	Calendar getNowTime(){
		Calendar calendar = Calendar.getInstance();
			int Hours = calendar.get(Calendar.HOUR_OF_DAY);
		int Minutes = calendar.get(Calendar.MINUTE);
		if(Minutes != 0 && Minutes != 30 ){
			if(Minutes < 30){
				calendar.set(Calendar.MINUTE, 30);
			}
			else{
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.HOUR, Hours + 1);
			}
				
		}
		if(Hours > 17){
			calendar.set(Calendar.HOUR, 8);
			calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+1);
		}
							
		
		return calendar;
	}
	
	public String getTime(){
		return datetime.format(getNowTime().getTime());
	}
	//to add hours
	public String getAddedTime(int addition){
		Calendar nowtime = getNowTime();
		nowtime.set(Calendar.HOUR_OF_DAY, nowtime.get(Calendar.HOUR_OF_DAY) + addition);
		return datetime.format(nowtime.getTime());
	}
	//to add selected date - time 
	public String getAddedTime(int flag, int addition){
		Calendar nowtime = getNowTime();
		nowtime.set(flag, nowtime.get(flag) + addition);
		return datetime.format(nowtime.getTime());
	}
	
	public String getZone(){
		Calendar nowtime = getNowTime();
		return timeZone.get(nowtime.get(Calendar.DST_OFFSET) / (60 * 1000));		 
	}
	
	public Date parseDate(String timetoparse){
		try {
			return datetime.parse(timetoparse);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean compareDates(String firstdate, String seconddate){
		
		try {
			if(firstdate == null){
				firstdate = getTime();
			}
			if(seconddate == null){
				seconddate = getTime();
			}
			return compareDates(datetime.parse(firstdate),datetime.parse(seconddate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Boolean compareDates(Date firstdate, Date seconddate){
		return firstdate.after(seconddate);		
	}
}
