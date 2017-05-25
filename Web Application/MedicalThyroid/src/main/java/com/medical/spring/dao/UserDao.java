package com.medical.spring.dao;

import com.medical.spring.model.User;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface UserDao {

	/**
	 * select username
	 * @param username - username
	 * @return - User object
	 */
	public User select(String username);

	/**
	 * find user
	 * @param username
	 * @return - true if exist username
	 */
	public boolean find(String username);

	/**
	 * insert user to database
	 * @param user - user object
	 */
	public void insert(User user);

	/**
	 * update user
	 * @param newUsername
	 * @param newPassword
	 * @param oldUsername
	 */
	public void edit(String newUsername, String newPassword, String oldUsername);

	/**
	 * delete user
	 * @param username
	 */
	public void delete(String username);

	/**
	 * delete role of user
	 * @param username
	 * @param role - role user
	 */
	public void deleteRole(String username, String role);

}
