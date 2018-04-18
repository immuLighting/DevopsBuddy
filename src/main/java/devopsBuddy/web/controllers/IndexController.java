package devopsBuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String home() {
		
		
		
		return "index";
	}
	@RequestMapping("/contact")
	public String contactpage() {
		return "contact";
	}
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
}
