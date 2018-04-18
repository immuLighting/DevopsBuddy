package devopsBuddy.web.controllers;

import java.util.List;
import java.util.ListIterator;

import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchControl {
private final TwitterTemplate twitterTemplate;
    
    public SearchControl(TwitterTemplate twitterTemplate) {
        this.twitterTemplate=twitterTemplate;
    }

	 @GetMapping(path = "tweet")
	    public String searchTwitter(Model model, @RequestParam String search) {
	    	
	    	
	        SearchResults results = twitterTemplate.searchOperations().search(
	        		new SearchParameters(search).lang("en"));
	        
	        List<Tweet> tweets = results.getTweets(); 
	        ListIterator<Tweet> itr=tweets.listIterator();  
	        int i=0;
	       while(itr.hasNext()==true) {
	    	   processTweetUserId(itr.next().getText());
	        System.out.println("and tweets read is :"+itr.next().getText());
	        i++;
	       }
	       
	       System.out.println("The total number of tweets are :"+i);
	        model.addAttribute("tweets", tweets);
	        model.addAttribute("search", search);
	        
	        return "search";
	    }

	 
	 public void processTweetUserId(String userID) {
		 System.out.print("The user id  :"+userID);
	 }

}