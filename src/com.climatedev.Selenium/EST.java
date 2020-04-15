package com.climatedev.selenium;

//Import date utils
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class EST {
	
	//Make time strings usable by main class
	public String est;
	public String o_time;
	public String c_time;
	public boolean marketOpen;
	public boolean marketClose;
	
	//Gets timezone in EST
	public void getEST() {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
		 dateFormat.setTimeZone(TimeZone.getTimeZone("EST5EDT")); 
		 est = dateFormat.format(new Date());
		 checkOTime(est);
		 checkCTime(est);
		 //System.out.println("Current time (EST): " + est);
		 //System.out.println("Is it 9 am EST? " + marketOpen);
	}
	
	//Check if the stock market is open or not, returning a boolean indicator
	public boolean checkOTime(String str) {
		o_time = "09:00:00";
		if(str.equals(o_time)) {
			marketOpen = true;
		}
		else {
			marketOpen = false;
		}
		return marketOpen;
	}
	
	public boolean checkCTime(String str) {
		c_time = "16:00:00";
		if(str.equals(c_time)) {
			marketClose = true;
		}
		else {
			marketClose = false;
		}
		return marketClose;
	}

}
