package com.chandra.dc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sun.misc.IOUtils;

public class CollectDataFromGoogleNews {

	public static void main(String[] args) throws IOException {
		    PrintWriter pw = new PrintWriter(new File("google-news-iphone.txt"));
		    int start = 0;
		    StringBuilder builder = new StringBuilder();;
		    String urlString = "https://ajax.googleapis.com/ajax/services/search/news?" +"v=1.0&q=iPhone%205s&userip=192.168.0.1&rsz=8";
			while(start < 50) {
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
			pw.write(builder.toString());
			pw.close();
			
			
			//JSONObject json = new JSONObject(builder.toString());

	}

}
