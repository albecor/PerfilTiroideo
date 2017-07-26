package com.spring.medical.service;

import java.util.Set;

import com.spring.medical.model.Role;

public interface RoleService {

	/**
	 * select role
	 * 
	 * @param id
	 * @return role object
	 */
	Role select(Integer id);

	/**
	 * select all roles
	 * 
	 * @return roles list
	 */
	Set<Role> selectAll();

	/**
	 * set role for user
	 * 
	 * @param user_id
	 * @param role_id
	 */
	void setRoleUser(Integer user_id, Integer role_id);

	/***
	 * remove role for user
	 * 
	 * @param user_id
	 * @param role_id
	 */
	void removeUserRole(Integer user_id, Integer role_id);

	/**
	 * delete role by id
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * count all administrators in the platform
	 * 
	 * @return
	 */
	Integer countAdministrators();

}