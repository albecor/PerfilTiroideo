package com.medical.spring.services;

import java.util.List;

import com.medical.spring.model.Lab;

public interface LabService {

	public void insert(Lab lab);

	public boolean find(String ndivalue);

	public void edit(Lab lab);

	public void delete(String ndivalue);

	public Lab select(String ndivalue);

	public String selectNdivalueById(int id);

	public List<Lab> selectAll();

}
