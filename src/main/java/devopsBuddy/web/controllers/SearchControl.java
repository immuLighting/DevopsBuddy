package devopsBuddy.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.SplittableRandom;

import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchParameters.ResultType;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class SearchControl {
private final TwitterTemplate twitterTemplate;
    public HashMap<Long,List<String>> hashMap;
    public SearchControl(TwitterTemplate twitterTemplate) {
        this.twitterTemplate=twitterTemplate;
    }

	 @GetMapping(path = "tweet")
	    public String searchTwitter(Model model, @RequestParam String search) {
	    	
	    	
	        SearchResults results = twitterTemplate.searchOperations().search(
	        		new SearchParameters(search).lang("en").count(200).resultType(ResultType.RECENT));
	        
	        List<Tweet> tweets = results.getTweets(); 
	        ListIterator<Tweet> itr=tweets.listIterator();  
	        int i=0;
	        for(;i<tweets.size();i++) {
	        processTweetUserId(tweets.get(i));
	        System.out.println("------------------------------------------");
	        }
	 
	       /* RestTemplate restTemplate = twitterTemplate.getRestTemplate();
	        String response = restTemplate.getForObject("https://api.twitter.com/1.1/search/tweets.json?q="+search, String.class);
	       System.out.println("JSON Response From Twitter: "+response);
	       */
	        System.out.println("The total number of tweets are :"+tweets.size());
	        model.addAttribute("tweets", tweets);
	        model.addAttribute("search", search);
	        
	        return "search";
	    }

	 
	 public void processTweetUserId(Tweet tweet) {
		 hashMap= new HashMap<Long,List<String>>();
		 String text = tweet.getUnmodifiedText();
		 List<String> listHash= new ArrayList<String>();
		 String [] words=text.split(" ");
		 
		 for (int i = 0, l = words.length; i  < l; i++) {
			 
			 String [] splitedString=null;
			 splitedString=checkHashInBetween(words[i]);
			 if(splitedString.length!=0)
			 {
				 for(int j=0;j<splitedString.length;j++) {
					 words[words.length+i]=splitedString[j];
			 }
			 if(words[i].startsWith("#"))//checkHashInBetween(words[i]))//||words[i].startsWith("@")||words[i].startsWith("https"))
			 {
				listHash.add(words[i]);
			   System.out.println("the matched = "+words[i]);
	 }
		 }
		 }
		 hashMap.put(tweet.getId(), listHash);
		
	 System.out.println("the total hashtags in the tweet"+ listHash.size());
	
		 }
	 public String [] checkHashInBetween(String hashString) {
		 String [] splitedString=null;
		 if (hashString.contains("#")){
			 	splitedString= hashString.split("#");
			 	return splitedString;
		 }
		return null;
		 
	 }
}