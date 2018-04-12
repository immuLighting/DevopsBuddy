package devopsBuddy;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class DevopsBuddyApplication {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DevopsBuddyApplication.class);

	public static void main(String[] args) {
		LOGGER.info("The class i s statrd");//log check
		
		SpringApplication.run(DevopsBuddyApplication.class, args);
		
		
	}
}
