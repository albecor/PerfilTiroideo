package com.medical.spring.dao;

import java.util.List;

import com.medical.spring.model.Lab;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface LabDao {

	/**
	 * insert lab to database
	 * @param lab - lab object
	 */
	public void insert(Lab lab);

	/**
	 * find user lab
	 * @param ndivalue - lab's ndivalue
	 * @return - true if exist user lab
	 */
	public boolean find(String ndivalue);

	/**
	 * find user lab by id
	 * @param id - identifier from user lab
	 * @return - true if exist  user lab
	 */
	public boolean findById(int id);

	/**
	 * update user lab
	 * @param lab - lab object
	 */
	public void edit(Lab lab);

	/**
	 * delete user lab
	 * @param ndivalue - lab's ndivalue
	 */
	public void delete(String ndivalue);

	/**
	 * update ndivalue from user lab
	 * @param oldNdivalue
	 * @param newNdivalue
	 */
	public void editNdivalue(String oldNdivalue, String newNdivalue);

	/**
	 * select user lab
	 * @param ndivalue - user lab's ndivalue
	 * @return - user lab object
	 */
	public Lab select(String ndivalue);

	/**
	 * select user lab by id
	 * @param id - identifier from user lab
	 * @return - user lab object
	 */
	public Lab selectById(int id);

	/**
	 * select ndivalue of user lab by id
	 * @param id - identifier from user lab
	 * @return - ndivalue
	 */
	public String selectNdivalueById(int id);

	/**
	 * list users lab
	 * @return - list users lab
	 */
	public List<Lab> selectAll();
}
