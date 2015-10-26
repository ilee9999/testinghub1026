package com.hkesports.matchticker.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

/**
 * @author manboyu
 *
 */
@Component("propertiesConfig")
public class PropertiesConfig {
	
	private static final Log logger = LogFactory.getLog(PropertiesConfig.class);
	
	private static final int PROPERTIES_REFRESH_DELAY = 10000;
	
	private PropertiesConfiguration properties;

	@PostConstruct
	private void init() throws IOException, ConfigurationException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		Resource resource = resourcePatternResolver.getResource("classpath:application.properties");
		logger.info("Loading the application.properties:" + resource.getURL());
		properties = new PropertiesConfiguration();
		properties.setListDelimiter('\\');
		properties.load(resource.getURL());
		FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
		strategy.setRefreshDelay(PROPERTIES_REFRESH_DELAY);
		properties.setReloadingStrategy(strategy);
	}
	
	public String getProperty(String key, String defaultValue) {
		if(StringUtils.isBlank(properties.getString(key))){
			return defaultValue;
		}
		return getProperty(key);
	}
	
	public String getProperty(String key) {
		return properties.getString(key);
	}
	
	public Integer getIntProperty(String key, Integer defaultValue){
		return properties.getInteger(key, defaultValue);
	}
	
	public Integer getIntProperty(String key){
		return properties.getInt(key);
	}
	
	public void setProperty(String key, Object value) {
		properties.setProperty(key, value);
	}
	
	public void save() throws ConfigurationException {
		properties.save();
	}
}
