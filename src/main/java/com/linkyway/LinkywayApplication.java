package com.linkyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableWebMvc
public class LinkywayApplication extends SpringBootServletInitializer {
    private final static String[] RESOURCE_LOCATIONS = {
            "classpath:/resources/",
            "classpath:/static/"
    };

    public static void main(String[] args) {
        SpringApplication.run(LinkywayApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations(RESOURCE_LOCATIONS);
            }
        };
    }

    @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        // Register Spring Social filter so that we can disconnect from providers
        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext
                .addFilter("hiddenHttpMethodFilter", HiddenHttpMethodFilter.class);
        hiddenHttpMethodFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}