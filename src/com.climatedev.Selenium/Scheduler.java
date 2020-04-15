package com.climatedev.selenium;

//Import packages
import java.io.IOException;
import java.util.concurrent.*;
 
public class Scheduler {
	
	//Declare common variables
	
	public static void main(String[] args) {
	    EST tu = new EST();
	
	    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
	    Runnable runnable = new Runnable() {
	        public void run()
	        {
	        	DJIA obj = new DJIA();
	        	EST tu = new EST();
	        	obj.getDJIA();
	        	tu.getEST();
	        	try {
	        	     obj.createCSV();
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	           System.out.println("Testing bitches!");
	        }
	    };
	    // Execute program at 9 am EST and 4 pm EST
	    int count = 0;
	    
	    //Catch for starting program at market open (unlikely)
	    //Runs program, schedules out for 7 hours, again in 17 hours, then loops continuously
	    if(tu.marketOpen == true){
	    	while(count >= 0) {
	    		 if(count == 0) {
	    	         service.schedule(runnable, count, TimeUnit.HOURS);
                     count +=7;
	    		}
	    		else if(count == 7) {
	    			 service.schedule(runnable, count, TimeUnit.HOURS);
                     count +=10;
	    		}
	    		else {
	    			 count = 0;
	    			 continue;
	    		}
	    	}
	    }
	    
	    //Catch for running program at neither stock threshold (likely)
	    else if(tu.marketOpen == false && tu.marketClose == false) {
	    	TimeUntil tiu = new TimeUntil();
	    	while(count >= 0) {
	    	    //Calculate time until next stock open/close
	    	    tiu.calcTime();
   	    	    service.schedule(runnable, tiu.wait, TimeUnit.HOURS);
   	    	    count++;
	    	}
	    }
	    
	    //Catch for running program at stock close (unlikely)
	    //Run immediately, then 17 hours later, then 7 hours later, then loop indefinitely
	    else if(tu.marketClose = true) {
	    	while(count >= 0) {
	    		if(count == 0) {
	    	        service.schedule(runnable, count, TimeUnit.HOURS);
	    	        count += 17;
	    		}
	    		else if(count == 17) {
	    			service.schedule(runnable, count, TimeUnit.HOURS);
	    			count -= 10;
	    		}
	    		else {
	    			service.schedule(runnable, count, TimeUnit.HOURS);
	    			count = 0;
	    		}
	    	}
	    }
	    
	    else {
	    	System.out.println("You should never see this message. Something is wrong :/");
	    }
	}
}