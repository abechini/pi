package com.esprit.bankPi.chat.config;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Component
public class MvcConfig  implements WebMvcConfigurer{
	
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	    }
}
