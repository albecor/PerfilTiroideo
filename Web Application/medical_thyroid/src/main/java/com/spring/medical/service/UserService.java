package com.spring.medical.service;

import java.util.List;

import com.spring.medical.model.PasswordResetToken;
import com.spring.medical.model.User;

public interface UserService {

	/**
	 * select user by identification number
	 * 
	 * @param ndivalue
	 * @return user object
	 */
	User selectByNdivalue(String ndivalue);

	/**
	 * select user by id
	 * 
	 * @param id
	 * @return user object
	 */
	User selectById(Integer id);

	/**
	 * insert uset in the database
	 * 
	 * @param user
	 */
	void insert(User user);

	/**
	 * update user in the database
	 * 
	 * @param user
	 */
	void update(User user);

	/**
	 * update password in the database
	 * 
	 * @param user
	 */
	void updatePassword(User user);

	/**
	 * delete user in the database
	 * 
	 * @param user
	 */
	void delete(User user);

	/**
	 * find user in the database
	 * 
	 * @param ndivalue
	 * @return true if exist user
	 */
	boolean find(String ndivalue);

	/**
	 * select all user
	 * 
	 * @return user list
	 */
	List<User> selectAll();

	/**
	 * insert token for to reset password in the database
	 * 
	 * @param user
	 * @param token
	 */
	void createPasswordResetTokenForUser(User user, String token);

	/**
	 * select token object by token data in the database
	 * 
	 * @param token
	 * @return PasswordResetToken object
	 */
	PasswordResetToken selectByToken(String token);

	/**
	 * delete token for reset password in the database
	 * 
	 * @param passwordResetToken
	 */
	void deletePasswordResetToken(PasswordResetToken passwordResetToken);

	/**
	 * delete token for reset password by user
	 * 
	 * @param user_id
	 */
	void deletePasswordResetTokenByUser(Integer user_id);

	/**
	 * delete token expired
	 */
	void deleteExpiredPasswordResetToken();

	String validatePasswordResetToken(Integer id, String token);

}