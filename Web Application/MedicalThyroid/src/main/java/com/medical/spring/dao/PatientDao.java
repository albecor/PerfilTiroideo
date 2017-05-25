package com.medical.spring.dao;

import java.util.List;

import com.medical.spring.model.Patient;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public interface PatientDao {

	/**
	 * insert patient to database
	 * @param patient - patient object
	 */
	public void insert(Patient patient);

	/**
	 * find ndivalue
	 * @param ndivalue - 
	 * @return - true if exists patient
	 */
	public boolean find(String ndivalue);

	/**
	 * update patient on database
	 * @param patient - patient object
	 */
	public void edit(Patient patient);

	/**
	 * update ndivalue 
	 * @param oldNdivalue
	 * @param newNdivalue
	 */
	public void editNdivalue(String oldNdivalue, String newNdivalue);

	/**
	 * delete patient
	 * @param ndivalue - patient's ndivalue
	 */
	public void delete(String ndivalue);

	/**
	 * select patient
	 * @param ndivalue - select patient from database
	 * @return - patient object
	 */
	public Patient select(String ndivalue);

	/**
	 * select ndivalue by id
	 * @param id - identifier user patient
	 * @return - ndivalue 
	 */
	public String selectNdivalueById(int id);

	/**
	 * list patient
	 * @return - list patient
	 */
	public List<Patient> selectAll();

}
