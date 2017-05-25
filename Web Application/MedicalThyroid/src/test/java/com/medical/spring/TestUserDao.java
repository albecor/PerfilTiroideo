package com.medical.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.dao.UserDao;
import com.medical.spring.model.User;

public class TestUserDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = new User();
		user.setUsername("root");
		user.setPassword("0000");

		user.setAdmin(true);
		userDao.insert(user);

		user.setPatient(true);
		userDao.insert(user);

		System.out.println(userDao.select("root").toString());

		userDao.deleteRole(user.getUsername(), "ROLE_ADMIN");

		System.out.println(userDao.select("root").toString());

		userDao.deleteRole(user.getUsername(), "ROLE_PATIENT");

		System.out.println(userDao.select("root").toString());

		((AnnotationConfigApplicationContext) context).close();
	}

}
