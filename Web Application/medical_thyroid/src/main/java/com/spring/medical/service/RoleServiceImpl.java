package com.spring.medical.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.medical.dao.RoleDao;
import com.spring.medical.model.Role;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public Role select(Integer id) {
		return roleDao.select(id);
	}

	@Override
	public Set<Role> selectAll() {
		return roleDao.selectAll();
	}

	@Override
	public void setRoleUser(Integer user_id, Integer role_id) {
		roleDao.setUserRole(user_id, role_id);
	}

	@Override
	public void removeUserRole(Integer user_id, Integer role_id) {
		roleDao.removeUserRole(user_id, role_id);
	}

	@Override
	public void delete(Integer id) {
		roleDao.delete(id);
	}

	@Override
	public Integer countAdministrators() {
		return roleDao.countAdministrators();
	}
}
