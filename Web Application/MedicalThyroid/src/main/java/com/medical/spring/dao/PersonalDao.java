package com.medical.spring.dao;

import java.util.List;

import com.medical.spring.model.Personal;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface PersonalDao {

	/**
	 * insert user personal to database
	 * @param personal - personal object
	 */
	public void insert(Personal personal);

	/**
	 * find personal
	 * @param ndivalue - personal's ndivalue
	 * @return - true if exists personal
	 */
	public boolean find(String ndivalue);

	/**
	 * update personal 
	 * @param personal - personal object
	 */
	public void edit(Personal personal);

	/**
	 * update ndivalue of personal
	 * @param oldNdivalue
	 * @param newNdivalue
	 */
	public void editNdivalue(String oldNdivalue, String newNdivalue);

	/**
	 * delete user personal
	 * @param ndivalue - personal's ndivalue
	 */
	public void delete(String ndivalue);

	/**
	 * select personal
	 * @param ndivalue - personal's ndivalue
	 * @return - personal object
	 */
	public Personal select(String ndivalue);

	/**
	 * select ndivalue by id
	 * @param id - identifier of personal object
	 * @return - ndivalue
	 */
	public String selectNdivalueById(int id);

	/**
	 * select list personal
	 * @return - list personal 
	 */
	public List<Personal> selectAll();

}
