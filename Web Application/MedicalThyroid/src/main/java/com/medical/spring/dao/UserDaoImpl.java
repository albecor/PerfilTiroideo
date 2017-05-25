package com.medical.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medical.spring.model.User;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	DAO dao;

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#insert(com.medical.spring.model.User)
	 */
	@Override
	public void insert(User user) {

		User userInDatabase = this.select(user.getUsername());

		if (userInDatabase.getUsername() == null) {
			String SQL_INSERT_USERS = "INSERT INTO users (username, password, enabled) VALUES ('" + user.getUsername()
					+ "','" + user.getPassword() + "'," + "true)";

			try {
				dao.executeUpdate(SQL_INSERT_USERS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String role = "";
		if (user.isAdmin() && !userInDatabase.isAdmin()) { // if user is admin
															// and not exists in
															// database then
															// ROLE_ADMIN
			role = "ROLE_ADMIN";
		} else if (user.isPatient() && !userInDatabase.isPatient()) {
			role = "ROLE_PATIENT";
		} else if (user.isPersonal() && !userInDatabase.isPersonal()) {
			role = "ROLE_PERSONAL";
		} else if (user.isLab() && !userInDatabase.isLab()) {
			role = "ROLE_LAB";
		}

		String SQL_INSERT_ROLE = "INSERT INTO user_roles (username, role) VALUES ('" + user.getUsername() + "','" + role
				+ "')";
		try {
			dao.executeUpdate(SQL_INSERT_ROLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#edit(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void edit(String newUsername, String newPassword, String oldUsername) {

		String SQL_UPDATE_USER = "UPDATE users SET username ='" + newUsername + "', password='" + newPassword
				+ "' WHERE username ='" + oldUsername + "'";

		try {
			dao.executeUpdate(SQL_UPDATE_USER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String username) {
		String SQL_DELETE_USER = "DELETE FROM users WHERE username='" + username + "'";

		try {
			dao.executeUpdate(SQL_DELETE_USER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#deleteRole(java.lang.String, java.lang.String)
	 */
	@Override
	public void deleteRole(String username, String role) {
		String SQL_DELETE_ROLE = "DELETE FROM user_roles WHERE username='" + username + "' AND " + " role = '" + role
				+ "'";
		try {
			dao.executeUpdate(SQL_DELETE_ROLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#select(java.lang.String)
	 */
	@Override
	public User select(String username) {

		User user = new User();

		String SQL_SELECT_BY_USERNAME = "SELECT * FROM users WHERE username='" + username + "'";

		String SQL_SELECT_ROLE = "SELECT * FROM user_roles WHERE username='" + username + "'";

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SQL_SELECT_BY_USERNAME).executeQuery();
			while (resultset.next()) {
				user.setPassword(resultset.getString("password"));
				user.setUsername(resultset.getString("username"));
			}

			resultset = connection.prepareStatement(SQL_SELECT_ROLE).executeQuery();
			while (resultset.next()) {
				switch (resultset.getString("role")) {

				case "ROLE_ADMIN":
					user.setAdmin(true);
					break;
				case "ROLE_PATIENT":
					user.setPatient(true);
					break;
				case "ROLE_PERSONAL":
					user.setPersonal(true);
					break;
				case "ROLE_LAB":
					user.setLab(true);
					break;
				}
			}

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.medical.spring.dao.UserDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String username) {
		String SELECT = "SELECT username FROM users WHERE username = '" + username + "'";

		boolean find = false;

		try {
			Connection connection = dao.getDatasource().getConnection();
			ResultSet resultset = connection.prepareStatement(SELECT).executeQuery();
			while (resultset.next()) {

				if (resultset.getString("username") != null) {
					find = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return find;
	}
}
