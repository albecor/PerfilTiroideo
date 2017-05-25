package com.medical.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.LabDao;
import com.medical.spring.model.Lab;
import com.medical.spring.model.User;

@Service("labService")
public class LabServiceImpl implements LabService {

	@Autowired
	LabDao labDao;

	@Autowired
	private UserService userService;

	@Override
	public void insert(Lab lab) {

		User user = new User();
		user.setUsername(lab.getNdivalue());
		user.setPassword(lab.getPassword());
		user.setLab(true);
		;
		userService.insert(user);

		labDao.insert(lab);
	}

	@Override
	public void edit(Lab lab) {
		userService.edit(lab.getNdivalue(), lab.getPassword(), labDao.selectNdivalueById(lab.getId()));
		labDao.edit(lab);
	}

	@Override
	public void delete(String ndivalue) {
		userService.delete(ndivalue, "ROLE_LAB");
		labDao.delete(ndivalue);
	}

	@Override
	public Lab select(String ndivalue) {
		return labDao.select(ndivalue);
	}

	@Override
	public List<Lab> selectAll() {
		return labDao.selectAll();
	}

	@Override
	public boolean find(String ndivalue) {
		return labDao.find(ndivalue);
	}

	public String selectNdivalueById(int id) {
		return labDao.selectNdivalueById(id);
	}

}
