package com.spring.medical.service;

import java.util.Locale;

import com.spring.medical.model.User;

public interface EmailService {

	/**
	 * send simple message
	 * 
	 * @param to
	 *            : email of the user
	 * @param subject
	 * @param text
	 */
	void sendSimpleMessage(String to, String subject, String text);

	/**
	 * send message with html included
	 * 
	 * @param to
	 * @param subject
	 * @param text
	 */
	void sendMimeMessage(String to, String subject, String text);

	/**
	 * send reset token
	 * 
	 * @param contextPath
	 *            : url server
	 * @param locale
	 *            : language
	 * @param user
	 *            : user object
	 */
	void sendResetPasswordToken(String contextPath, Locale locale, User user);

}
