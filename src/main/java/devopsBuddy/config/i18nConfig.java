package devopsBuddy.config;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import devopsBuddy.DevopsBuddyApplication;

@Configuration
public class i18nConfig {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DevopsBuddyApplication.class);
	@Bean 
	 public ReloadableResourceBundleMessageSource messageSource() {
		LOGGER.info("The class i s statrd");
        ReloadableResourceBundleMessageSource resourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("classpath:i18n/messages");
        // Checks for new messages every 30 minutes
        resourceBundleMessageSource.setCacheSeconds(1800);
        return resourceBundleMessageSource;
    }

}
