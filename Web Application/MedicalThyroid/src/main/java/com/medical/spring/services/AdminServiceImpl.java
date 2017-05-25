package com.medical.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.AdminDao;
import com.medical.spring.model.Admin;
import com.medical.spring.model.User;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	@Autowired
	UserService userService;

	@Override
	public void insert(Admin admin) {

		User user = new User();
		user.setUsername(admin.getNdivalue());
		user.setPassword(admin.getPassword());
		user.setAdmin(true);

		userService.delete("root", "ROLE_TEMPORAL"); // delete root
		userService.insert(user); // insert user admin to database

		adminDao.insert(admin); // insert admin to database
	}

	@Override
	public void edit(Admin admin) {
		userService.edit(admin.getNdivalue(), admin.getPassword(), adminDao.selectById(admin.getId()).getNdivalue());
		adminDao.edit(admin);
	}

	@Override
	public void delete(String ndivalue) {
		adminDao.delete(ndivalue);
	}

	@Override
	public Admin select(String ndivalue) {
		return adminDao.select(ndivalue);
	}

	@Override
	public List<Admin> selectAll() {
		return adminDao.selectAll();
	}

	@Override
	public Admin selectById(int Id) {
		return adminDao.selectById(Id);
	}

	@Override
	public boolean find(String ndivalue) {
		return adminDao.find(ndivalue);
	}

	@Override
	public boolean findById(int id) {
		return adminDao.findById(id);
	}

	@Override
	public String selectNdivalueById(int id) {
		return adminDao.selectNdivalueById(id);
	}
}
