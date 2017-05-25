package com.medical.spring.services;

import com.medical.spring.model.User;

public interface UserService {

	public User select(String username);

	public boolean find(String username);

	public void insert(User user);

	public void delete(String username, String role);

	public void edit(String newUsername, String newPassword, String oldUsername);

	// public void editUsername(String newUsername, String oldUsername );

}
