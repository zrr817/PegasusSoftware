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


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//Note: May not work for all mesowest interfaces due to different tabular formats

public class MesonetScraper {

	public static void main(String[] args) {
		Scanner address = new Scanner (System.in);
		System.out.println("Enter the complete url (including http://) of the site you would like to parse:");
		String html = address.nextLine();
		try {
			Document doc = Jsoup.connect(html).get();
			System.out.printf("Title: %s", doc.title());
			//Try to print site content
			System.out.println("");
			System.out.println("Writing html contents to 'Mesonet.txt'...");
			//Save html contents to text file
			PrintWriter outputfile = new PrintWriter("Mesonet.txt");
			outputfile.print(doc.outerHtml());
			outputfile.close();
			
			//Select station data
			Loop1:
				for(Element row : doc.select("table.newtable tbody.tr")) {
					final String obs = row.select(".td").text();
					System.out.println(obs + "I'm a test!");
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Complete.");
	}
}

