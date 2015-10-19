package com.chandra.dc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class CollectDataFromAmazonIndia {

	public static void main(String[] args) throws IOException {
		    PrintWriter pw = new PrintWriter(new File("amazon-reviews-iphone-5s.txt"));
		    int start = 0;
		    StringBuilder builder = new StringBuilder();
		    int pageNumber = 1;
		    int buttonLink = 1;
		    String urlString = "http://www.amazon.in/product-reviews/B00FXLCD38/ref=cm_cr_pr_btm_link_2?ie=UTF8&showViewpoints=1&sortBy=helpful&reviewerType=all_reviews&formatType=all_formats&filterByStar=all_stars&pageNumber=2";
			while(pageNumber < 14) {
				urlString = "http://www.amazon.in/product-reviews/B00FXLCD38/ref=cm_cr_pr_btm_link_"+buttonLink+"?ie=UTF8&showViewpoints=1&sortBy=helpful&reviewerType=all_reviews&formatType=all_formats&filterByStar=all_stars&pageNumber="+pageNumber;
				URL url = new URL(urlString);
				URLConnection connection = url.openConnection();
				connection.addRequestProperty("Referer", "localhost");
				
				String line;
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
				builder.append(line);
				}
				start = start + 8;
				pageNumber++;
				buttonLink++;
			}
			pw.write(builder.toString());
			pw.close();
			
			
			//JSONObject json = new JSONObject(builder.toString());

	}

}