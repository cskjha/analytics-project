package com.chandra.dc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CollectDataFromGoogleNews {

	public static void main(String[] args) throws IOException {
		    PrintWriter pw = new PrintWriter(new File("google-news-iphone.txt"));
		    int start = 0;
		    StringBuilder builder = new StringBuilder();;
		    String urlString = "https://ajax.googleapis.com/ajax/services/search/news?" +"v=1.0&q=iPhone%205s&userip=192.168.0.1&rsz=8&hl=en&ned=in";
			while(start < 200) {
				URL url = new URL(urlString+"&start="+start);
				URLConnection connection = url.openConnection();
				connection.addRequestProperty("Referer", "localhost");
				
				String line;
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
				builder.append(line);
				}
				start = start + 8;
			}
			String fetchedData = builder.toString();
			String startToken = "\"content\":\"";
			int index = fetchedData.indexOf(startToken,0);;
			while (index > -1) {
				int endIndex = fetchedData.indexOf("\"",index+(startToken.length()));
				String newsContent = fetchedData.substring(index+(startToken.length()), endIndex);
				pw.write(newsContent+"\n\n");
				index = fetchedData.indexOf(startToken,endIndex);
			}
			
			pw.close();
			
			
			//JSONObject json = new JSONObject(builder.toString());

	}

}
