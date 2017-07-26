package com.spring.medical.dao;

import java.util.List;

import com.spring.medical.model.Organization;

public interface OrganizationDao {

	/**
	 * insert organization in the database
	 * 
	 * @param organization
	 */
	void insert(Organization organization);

	/**
	 * update organizatio in the database
	 * 
	 * @param organization
	 */
	void edit(Organization organization);

	/**
	 * fin
	 * 
	 * @param organization
	 */
	void find(Organization organization);

	/**
	 * select all organizations
	 * 
	 * @return
	 */
	List<Organization> selectAll();

	/**
	 * select organization by id
	 * 
	 * @param id
	 * @return
	 */
	Organization selectById(Integer id);

}
