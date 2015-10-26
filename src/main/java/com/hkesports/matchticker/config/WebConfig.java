package com.hkesports.matchticker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author manboyu
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hkesports.matchticker.web.controller", useDefaultFilters = false, includeFilters = {
		 @ComponentScan.Filter(type = FilterType.ANNOTATION, value = org.springframework.stereotype.Controller.class)
})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
	}
	
	@Bean
    public InternalResourceViewResolver jspViewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setPrefix("/views/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
