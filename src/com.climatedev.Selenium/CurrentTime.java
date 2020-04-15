package com.climatedev.selenium;


//Import Packages
import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime extends EST {

	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); 
		String time = dateFormat.format(new Date());
		return time;
	}
}
