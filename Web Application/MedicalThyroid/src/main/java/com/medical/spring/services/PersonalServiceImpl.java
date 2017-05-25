package com.medical.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.PersonalDao;
import com.medical.spring.model.Personal;
import com.medical.spring.model.User;

@Service("personalService")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	PersonalDao personalDao;

	@Autowired
	private UserService userService;

	@Override
	public void insert(Personal personal) {
		User user = new User();
		user.setUsername(personal.getNdivalue());
		user.setPassword(personal.getPassword());
		user.setPersonal(true);

		userService.insert(user); // insert user personal to database
		personalDao.insert(personal); // insert personal to database

	}

	@Override
	public void edit(Personal personal) {
		userService.edit(personal.getNdivalue(), personal.getPassword(),
				personalDao.selectNdivalueById(personal.getId()));
		personalDao.edit(personal);
	}

	@Override
	public void delete(String ndivalue) {

		userService.delete(ndivalue, "ROLE_PERSONAL");
		personalDao.delete(ndivalue);
	}

	@Override
	public Personal select(String ndivalue) {
		return personalDao.select(ndivalue);
	}

	@Override
	public List<Personal> selectAll() {
		return personalDao.selectAll();
	}

	@Override
	public boolean find(String ndivalue) {
		return personalDao.find(ndivalue);
	}

	@Override
	public String selectNdivalueById(int id) {
		return personalDao.selectNdivalueById(id);
	}

}
