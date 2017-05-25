package com.medical.spring.services;

import java.util.List;

import com.medical.spring.model.Admin;

public interface AdminService {

	public void insert(Admin admin);

	public boolean find(String ndivalue);

	public boolean findById(int id);

	public void edit(Admin admin);

	public void delete(String ndivalue);

	public Admin select(String ndivalue);

	public Admin selectById(int id);

	public String selectNdivalueById(int id);

	public List<Admin> selectAll();

}
