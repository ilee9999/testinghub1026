package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.service.MailService;

/**
 * @author manboyu
 *
 */
@Service("mailService")
public class MailServiceImpl implements MailService {
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;

	@Resource(name = "mailSender")
	private JavaMailSender mailSender;
	
	@Override
	public void sendMail(String body) {
		sendMail(propertiesConfig.getProperty("mail.subject"), body);
	}

	@Override
	public void sendMail(String subject, String body) {
		String[] to = StringUtils.split(propertiesConfig.getProperty("mail.to"), ",");
		sendMail(subject, body, to);
	}

	@Override
	public void sendMail(final String subject, final String body, final String... to) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(new InternetAddress(propertiesConfig.getProperty("mail.from"), propertiesConfig.getProperty("mail.from.name")));
				message.setSubject(subject);
				message.setText(body, true);
			}
		};
		mailSender.send(preparator);
	}
}
