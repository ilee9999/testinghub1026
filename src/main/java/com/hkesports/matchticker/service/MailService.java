package com.hkesports.matchticker.service;

/**
 * @author manboyu
 *
 */
public interface MailService {
	
	public void sendMail(String body);

	public void sendMail(String subject, String body);
	
	public void sendMail(String subject, String body, String... to);
}
