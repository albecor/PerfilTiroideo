package com.medical.spring.dao;

import java.util.List;

import com.medical.spring.model.Admin;

/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface AdminDao {

	/**
	 * @param admin - insert admin to database
	 */
	public void insert(Admin admin);

	/**
	 * 
	 * @param ndivalue - ndivalue of user
	 * @return - true if user is found
	 */
	public boolean find(String ndivalue);

	/**
	 * @param id - user's identifier from database
	 * @return - true if user is found
	 */
	public boolean findById(int id);

	/**
	 * - update user admin in the database
	 * @param admin - admin object
	 */
	public void edit(Admin admin);

	/**
	 * - delete user from database
	 * @param ndivalue - ndivalue of user
	 */
	public void delete(String ndivalue);

	/**
	 * - Update user's ndivalue in the database
	 * @param oldNdivalue - old ndivalue of user
	 * @param newNdivalue - new ndivalue of user
	 */
	public void editNdivalue(String oldNdivalue, String newNdivalue);

	/**
	 * @param ndivalue -  user's ndivalue
	 * @return - admin object
	 */
	public Admin select(String ndivalue);

	/**
	 * @param id - user's identifier from database
	 * @return - admin object 
	 */
	public Admin selectById(int id);

	/**
	 * @param id - user's identifier from database
	 * @return - ndivalue of admin
	 */
	public String selectNdivalueById(int id);

	/**
	 * @return - list admin
	 */
	public List<Admin> selectAll();
}
