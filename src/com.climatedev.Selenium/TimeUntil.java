package com.climatedev.selenium;

import java.text.ParseException;
//Import packages
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.lang.Math;
import java.time.LocalTime;
import java.time.Duration;

public class TimeUntil extends EST {
	
	//Create commonly used EST function
	EST est = new EST();
	public static LocalTime d1;
	public static LocalTime  d2;
	public static LocalTime  d3;
	public static long diff;
	public int wait = 0;
	
	//Main time calculator
	public void calcTime() {
		est.getEST();
	    try {
			d1 = LocalTime.parse(est.est);
			d2 = LocalTime.parse(est.o_time);
			d3 = LocalTime.parse(est.c_time);
			Duration duration = Duration.between(d1, d2);
			diff = duration.toHours();
			if(diff > 0 || diff < -7) {
				tillOpen();
			}
			else {
				tillClose();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
    public int tillOpen(){
    	if(diff > 0) {
             wait = (int) diff;
    	     return wait;
    	}
    	else {
    		int x = Math.abs((int) diff);
    		wait = 24 - x;
    		return wait;
    	}
    }
    
    public int tillClose() {
    	wait = Math.abs((int) diff);
    	return wait;
    }
}
