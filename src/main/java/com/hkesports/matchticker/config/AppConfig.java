package com.hkesports.matchticker.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.hkesports.matchticker.common.utils.JsoupUtils;

/**
 * @author manboyu
 *
 */
@Configuration
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:datasource.properties")
})
public class AppConfig {

	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JsoupUtils jsoupUtils() {
		return new JsoupUtils(propertiesConfig.getIntProperty("api.max.retry.count"), propertiesConfig.getIntProperty("api.retry.sleep"), propertiesConfig.getIntProperty("jsoup.timeout"), propertiesConfig.getProperty("jsoup.filepath"));
	}
}
