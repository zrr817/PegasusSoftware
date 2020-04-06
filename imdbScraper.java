package com.climatedev.WebScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class imdbScraper {
	//Try to scrape IMDB top 250
	public static void main(String[] args) throws Exception {
		Document document = Jsoup.connect("https://www.imdb.com/chart/top/").get();
		System.out.println(document.outerHtml());
	}
}
