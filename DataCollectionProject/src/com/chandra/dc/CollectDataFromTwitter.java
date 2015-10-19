package com.chandra.dc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class CollectDataFromTwitter {

	public static void main(String[] args) {
		Twitter twitter = new TwitterFactory().getInstance();

	    AccessToken accessToken = new AccessToken("357422083-FS62jCnCECDQ0o1HeT22Uu9da3V4KMSQWIanZilA", "pepFF2nM6TqRap4pNweLbGv9g39sYRP2uV6wuLdUKLM2h");
	    twitter.setOAuthConsumer("CdfoEfSG69w9sWzd3ojUUfp3r", "Ngmos0tDMxj83s1dzGJchLQfGkcd3j8pbshh8oAT7dajyEazhl");
	    twitter.setOAuthAccessToken(accessToken);

	    try {
	    	PrintWriter pw = new PrintWriter(new File("twitter-tweets-iphone-5s.txt"));
	        Query query = new Query("iphone 5 s");
	        query.setCount(100);
	        query.setLang("en");        

	        QueryResult result;
	        
	        result = twitter.search(query);
	        int totalSearch = 0;
	        while (result.getTweets().size() != 0 && totalSearch < 1000) {

	            List<Status> tweets = result.getTweets();
	            Long minId = Long.MAX_VALUE;

	            for (Status tweet : tweets) {
	            	String tweetText = tweet.getText();
	            	if(tweetText != null && tweetText.length() > 100) {
	            		pw.write(tweet.getText()+"\n\n");
	            		totalSearch++;
	            	}
	            	
	                if (tweet.getId() < minId)
	                minId = tweet.getId();
	            }
	            query.setMaxId(minId-1);
	            result = twitter.search(query);
	       }
	        
	        
	        
	        
//	        List<Status> tweets = result.getTweets();
//	        for (Status tweet : tweets) {
//	        	String tweetText =tweet.getText();
//	        	//if(tweetText != null && tweetText.length() >100) {
//	        		//System.out.println(tweetText+"\n\n");
//	        		pw.write(tweetText+"\n\n");
//	        	//}
//	            
//	        }
	        pw.close();
	    }
	    catch (TwitterException te) {
	        te.printStackTrace();
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
