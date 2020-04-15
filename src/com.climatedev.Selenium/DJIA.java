package com.climatedev.selenium;
//Built in Java 1.7 due to Selenium compatabilities

//Import packages
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

//Import Selenium pacakages
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVWriter;

public class DJIA {
	
	//Instantiate common variables
	WebDriver driver;
	String url;
	static String stock;
	Scanner input;
	boolean error;
	int uses = 0;
	static FileWriter csvWriter;
	static File f;
	static Path path;
    //@SuppressWarnings("1586487528")
	
	//Need to permanently create arrays that will never be overriden by method calls
	public static ArrayList<String> o_values = new ArrayList<String>();
	public static ArrayList<String> c_values = new ArrayList<String>();
	
	public void invokeBrowser() {
		try {
			//Point Selenium to Chrome Driver file
			 File file = new File("C:\\Users\\zrr81\\eclipse-workspace\\WebScraper\\Selenium\\chromedriver.exe");
			 System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			 
			 //Initialize Chrome driver and perform maintenance functions
			 driver = new ChromeDriver();
			 driver.manage().deleteAllCookies();
			 uses ++;
			 
			 //Execute parseData code
			 parseData();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	     
	//Try to connect, include catch for typos/errors
	public void parseData() {
	     try {
			 String url = "https://www.cnbc.com/quotes/?symbol=.DJI";
	         driver.get(url);
	         elementLocator();
	         
	     }
	     //Specific catch for an invalid site (e.g. a dead link)
	     catch(InvalidArgumentException e) {
	    	 System.out.println("He's dead Jim! :/");
	     }
	     //Catch for all other exceptions
	     catch(Exception ex) {
	    	 System.out.println("Check your syntax partner!");
	     }
	}
	
	public void elementLocator() {
		try {
			WebElement TxtBoxContent = driver.findElement(By.cssSelector("td > .last:nth-child(1)"));
			stock = TxtBoxContent.getText();
			System.out.println("Current DJIA PPS: $" +stock);
			Thread.sleep(3000);
			closeBrowser();
		//Catch specific exception for html element not found
		} catch (NoSuchElementException e) {
			System.out.println("Selected element not found");
			error = true;
	    //General exception catch
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (error == true) {
				closeBrowser();
			}
		}
	}
	
	public void makeCSV() throws IOException {
		//Create output file only if it does not already exist
		File f = new File("DJIA.csv");
		path = Paths.get("C:\\Users\\zrr81\\eclipse-workspace\\SelTest\\DJIA.csv");
		if(!f.exists()) {
			try { 
		        //Create FileWriter object with file as parameter 
		        String fid = "DJIA.csv";
		        CSVWriter writer = new CSVWriter(new FileWriter(fid, true));
		        String[] headers = "Stock, Opening Price, Closing Price".split(",");
		        writer.writeNext(headers);
		        writer.close();
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else {
		}
	}
	public void createCSV() throws IOException {
		//Insert if else logic to import to proper array
		makeCSV();
		o_values.add(stock);
		o_values.add("test");
		c_values.add("#Empty");
		c_values.add("test");
		
		//Import arrays
		for(int i=0; i < c_values.size(); i++) {
			if(i == 0) {
				Files.write(path, "DJIA".getBytes(), StandardOpenOption.APPEND);
				Files.write(path, ",".getBytes(), StandardOpenOption.APPEND);
			}
			else {
				Files.write(path, " ".getBytes(), StandardOpenOption.APPEND);
				Files.write(path, ",".getBytes(), StandardOpenOption.APPEND);
			}
			String input1 = appendDQ(o_values.get(i));
			Files.write(path, input1.getBytes(), StandardOpenOption.APPEND);
			Files.write(path, ",".getBytes(), StandardOpenOption.APPEND);
			Files.write(path, c_values.get(i).getBytes(), StandardOpenOption.APPEND);
			Files.write(path, "\n".getBytes(), StandardOpenOption.APPEND);
		}
		System.out.println("Stock data sucessfully written to file.");
	}
	
	//Create private method to append strings with commas
	private static String appendDQ(String str) {
	    return "\"" + str + "\"";
	}
	
	public void closeBrowser() {
		//If only 1 window is open, close the window
		if(uses == 1) {
			driver.close();
		}
		//Otherwise, close the whole browser
		else {
			driver.quit();
		}
	}
	
	//Invoke methods
	public void getDJIA() {
		DJIA myObj = new DJIA();
		myObj.invokeBrowser();
	}
}
