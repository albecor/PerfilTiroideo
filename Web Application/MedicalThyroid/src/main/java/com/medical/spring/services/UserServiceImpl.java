package com.medical.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.AdminDao;
import com.medical.spring.dao.PatientDao;
import com.medical.spring.dao.PersonalDao;
import com.medical.spring.dao.UserDao;
import com.medical.spring.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	AdminDao adminDao;

	@Autowired
	PersonalDao PersonalDao;

	@Autowired
	PatientDao patientDao;

	@Override
	public User select(String username) {
		return userDao.select(username);
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public void edit(String newUsername, String newPassword, String oldUsername) {

		// Edit all Ndivalue user in roles if exists
		if (!oldUsername.equals(newUsername)) {

			User oldUser = userDao.select(oldUsername);

			if (oldUser.isAdmin())
				adminDao.editNdivalue(oldUsername, newUsername);

			if (oldUser.isPatient())
				patientDao.editNdivalue(oldUsername, newUsername);

			if (oldUser.isPersonal())
				PersonalDao.editNdivalue(oldUsername, newUsername);
		}

		// edit user and password
		userDao.edit(newUsername, newPassword, oldUsername);
	}

	@Override
	public void delete(String username, String role) {

		userDao.deleteRole(username, role);

		User user = userDao.select(username);

		if (!(user.isAdmin() || user.isPatient() || user.isPersonal())) {// if
																			// user
																			// not
																			// exist
																			// role
																			// is
																			// deleted
			userDao.delete(username);
		}

	}

	@Override
	public boolean find(String username) {
		return userDao.find(username);
	}
}
