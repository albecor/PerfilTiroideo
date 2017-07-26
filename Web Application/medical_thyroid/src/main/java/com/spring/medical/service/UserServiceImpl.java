package com.spring.medical.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.medical.dao.RoleDao;
import com.spring.medical.dao.UserDao;
import com.spring.medical.model.PasswordResetToken;
import com.spring.medical.model.Role;
import com.spring.medical.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User selectByNdivalue(String ndivalue) {
		User user = userDao.selectByNdivalue(ndivalue);
		user.setRoles(roleDao.selectByUser(user.getId()));
		return user;
	}

	@Override
	public void insert(User user) {
		User entity = userDao.selectByNdivalue(user.getNdivalue());
		if (entity.getId() == null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userDao.insert(user);
			User entity2 = userDao.selectByNdivalue(user.getNdivalue());
			for (Role role : user.getRoles()) {
				roleDao.setUserRole(entity2.getId(), role.getId());
			}
		}
	}

	@Override
	public void update(User user) {
		User entity = userDao.selectById(user.getId());
		if (entity != null) {
			if (!user.getPassword().equals(entity.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			userDao.update(user);

			// remove old roles
			Set<Role> roles = roleDao.selectByUser(user.getId());
			for (Role role : roles) {
				roleDao.removeUserRole(user.getId(), role.getId());
			}

			// set new roles
			for (Role role : user.getRoles()) {
				roleDao.setUserRole(user.getId(), role.getId());
			}
		}
	}

	@Override
	public void delete(User user) {

		Set<Role> roles = roleDao.selectByUser(user.getId());

		for (Role role : roles) {
			roleDao.removeUserRole(user.getId(), role.getId());
		}
		userDao.delete(user);
	}

	@Override
	public boolean find(String ndivalue) {
		return userDao.find(ndivalue);
	}

	@Override
	public User selectById(Integer id) {
		User user = userDao.selectById(id);
		user.setRoles(roleDao.selectByUser(user.getId()));
		return user;
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		userDao.createPasswordResetTokenForUser(user, token);
	}

	@Override
	public PasswordResetToken selectByToken(String token) {
		return userDao.selectByToken(token);
	}

	@Override
	public void deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		userDao.deletePasswordResetToken(passwordResetToken);
	}

	@Override
	public String validatePasswordResetToken(Integer id, String token) {
		PasswordResetToken passToken = selectByToken(token);
		if ((passToken.getId() == null) || (passToken.getUser().getId() != id)) {
			return "invalidToken";
		}

		Calendar cal = Calendar.getInstance();
		if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			return "expired";
		}

		User user = passToken.getUser();
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
		SecurityContextHolder.getContext().setAuthentication(auth);
		return null;
	}

	@Override
	public void updatePassword(User user) {
		System.out.println(user);
		User entity = userDao.selectById(user.getId());
		if (entity.getId() != null) {

			if (!user.getPassword().equals(entity.getPassword())) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			userDao.updatePassword(user);
		}

		deletePasswordResetTokenByUser(user.getId());
	}

	@Override
	public void deletePasswordResetTokenByUser(Integer user_id) {
		userDao.deletePasswordResetTokenByUser(user_id);
	}

	@Override
	public void deleteExpiredPasswordResetToken() {
		userDao.deleteExpiredPasswordResetToken();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spring.medical.service.UserService#selectAll()
	 */
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
