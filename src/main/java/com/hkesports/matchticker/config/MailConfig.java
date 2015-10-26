package com.hkesports.matchticker.config;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author manboyu
 *
 */
@Configuration
public class MailConfig {

	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(propertiesConfig.getProperty("mail.host"));
        mailSender.setPort(propertiesConfig.getIntProperty("mail.port"));
        mailSender.setUsername(propertiesConfig.getProperty("mail.username"));
        mailSender.setPassword(propertiesConfig.getProperty("mail.password"));
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());
        
        return mailSender;
    }
	
	private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "false");
        properties.setProperty("mail.debug.auth", "false");
        return properties;
    }
}
