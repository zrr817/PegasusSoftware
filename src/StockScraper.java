package com.climatedev.WebScraper;

//Import necessary packages
import org.jsoup.Jsoup;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;

//Pull site title and brief description to verify you are getting what you are expecting
//Then, pulls the html for that site
public class StockScraper {
	
	public static void main(String[] args) {
		Scanner address = new Scanner (System.in);
		System.out.println("Enter the complete url (including http://) of the site you would like to parse:");
		String html = address.nextLine();
		try {
			Document doc = Jsoup.connect(html).get();
			System.out.printf("Title: %s", doc.title());
			//Try to print site content
			System.out.println("");
			System.out.println("Writing html contents to 'html.txt'...");
			//Save html contents to text file
			PrintWriter outputfile = new PrintWriter("html.txt");
			outputfile.print(doc.outerHtml());
			outputfile.close();
			
			//Select stock data you want to retrieve
			Scanner stock = new Scanner (System.in);
			System.out.println("Enter the name of the stock you want to check");
			String name = stock.nextLine();
			
			//Pull data from CNBC Markets
			Loop1:
			for(Element money : doc.select("tbody")) {
				final String market = money.select("tbody").text();
				final String index = money.select("td").text();
			    String [] funds = index.split(" ");
				//System.out.println(Arrays.toString(funds));
				Pattern pattern = Pattern.compile(" ");
				funds = pattern.split(index);
				int dim = funds.length;
				System.out.println(dim);
				//System.out.println(Arrays.deepToString(funds));
				for (var i = 0; i < dim; i++)
					     if (funds[i].equals(name)) {
					    	 System.out.println(funds[i]);
					    	 break Loop1;
					     }
					   //if (funds[i] == ""){
					      //funds.remove(i);
					      //break;
					  
				       //if (funds[i] == "--") {
				    	   //funds.splice(i,1);
				    	   //break;  
				      // }
					
				//Trial and error section
				//funds = funds.filter(function(el) { return el.funds != ""; }));
				//int scalar = index.length();
				//System.out.println(scalar);
				//for (var i = 0; i < scalar; i++)
					//if (market == name) {
						//System.out.println(market);
						//break;
					//}
				System.out.println(market);
				//System.out.println(index);
			}
			
		//Catch any IO Exceptions and print the error that occurred for debugging
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	