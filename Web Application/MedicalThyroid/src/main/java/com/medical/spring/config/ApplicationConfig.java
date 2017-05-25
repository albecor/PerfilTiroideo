package com.medical.spring.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.medical.spring.dao.DAO;
import com.medical.spring.resources.SendMail;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */

@Configuration
@ComponentScan(basePackages = "com.medical.spring")
public class ApplicationConfig {
	
	/**
	 * bean dao with parameter to connection
	 * @return - bean dao
	 */
	@Bean
	public DAO dao() {	

		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}

		DAO dao = new DAO(properties.getProperty("driverClassName"), properties.getProperty("url"),
				properties.getProperty("database"), properties.getProperty("username"),
				properties.getProperty("password"), properties.getProperty("scriptDatabase"));
		return dao;
	}
	

	/**
	 * charge sender's email
	 * @return sendMail
	 */
	@Bean
	public SendMail sendMail() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("sender.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
		SendMail sendMail = new SendMail(properties.getProperty("sender"), properties.getProperty("senderPass"));
		return sendMail;
	}

	/**
	 * charge messages to validate forms
	 * @return - bean messageSource
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		rb.setBasenames(new String[] { "messages/messages", "messages/validation" });
		return rb;
	}

}