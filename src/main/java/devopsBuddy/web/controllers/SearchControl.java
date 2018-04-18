package devopsBuddy.web.controllers;

import java.util.List;
import java.util.ListIterator;

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
	        for(;i<20;i++)
	        processTweetUserId(tweets.get(i).getUnmodifiedText());
	 
	        RestTemplate restTemplate = twitterTemplate.getRestTemplate();

	        //More Query Options @ https://dev.twitter.com/rest/reference/get/search/tweets    
	        String response = restTemplate.getForObject("https://api.twitter.com/1.1/search/tweets.json?q="+search, String.class);
	       System.out.println("JSON Response From Twitter: "+response);
	       
	       
	       System.out.println("The total number of tweets are :"+tweets.size());
	        model.addAttribute("tweets", tweets);
	        model.addAttribute("search", search);
	        
	        return "search";
	    }

	 
	 public void processTweetUserId(String text) {
		 System.out.println(text);
		 String [] words=text.split(" ");
		 for (int i = 0, l = words.length; i  < l; i++)
		        System.out.println("the string "+i+" = "+words[i] );
	 }

}