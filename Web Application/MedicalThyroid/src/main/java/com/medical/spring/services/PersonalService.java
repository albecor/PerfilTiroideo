package com.medical.spring.services;

import java.util.List;

import com.medical.spring.model.Personal;

public interface PersonalService {

	public void insert(Personal personal);

	public boolean find(String ndivalue);

	public void edit(Personal personal);

	public void delete(String ndivalue);

	public Personal select(String ndivalue);

	public String selectNdivalueById(int id);

	public List<Personal> selectAll();

}
