package com.spring.medical.dao;

import java.util.Set;

import com.spring.medical.model.Role;

public interface RoleDao {

	/**
	 * delete user
	 * 
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * set role to user into the roles table
	 * 
	 * @param user_id
	 * @param role_id
	 */
	void setUserRole(Integer user_id, Integer role_id);

	/**
	 * remover role user in the roles table
	 * 
	 * @param user_id
	 * @param role_id
	 */
	void removeUserRole(Integer user_id, Integer role_id);

	/**
	 * select role by id
	 * 
	 * @param id
	 * @return role
	 */
	Role select(Integer id);

	/**
	 * select roles by user
	 * 
	 * @param id
	 * @return role list
	 */
	Set<Role> selectByUser(Integer id);

	/**
	 * select all roles
	 * 
	 * @return role list
	 */
	Set<Role> selectAll();

	/**
	 * count user with administrator role
	 * 
	 * @return
	 */
	Integer countAdministrators();

}
