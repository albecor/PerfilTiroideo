package com.spring.medical.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

import com.spring.medical.dao.DAO;
import com.spring.medical.service.UserService;

@Controller
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	DAO dao;

	@Autowired
	UserService userservice;

	/**
	 * on startup, create to database if not exist
	 */
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		dao.createDataBase();
	}
}
