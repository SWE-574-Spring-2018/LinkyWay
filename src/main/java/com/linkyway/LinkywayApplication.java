package com.linkyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableWebMvc
public class LinkywayApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LinkywayApplication.class, args);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Register Spring Social filter so that we can disconnect from providers
		FilterRegistration.Dynamic hiddenHttpMethodFilter =
						servletContext.addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
		hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
	}
}
