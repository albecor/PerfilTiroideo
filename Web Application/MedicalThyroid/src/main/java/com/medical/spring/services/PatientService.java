package com.medical.spring.services;

import java.util.List;

import com.medical.spring.model.Patient;

public interface PatientService {

	public void insert(Patient patient);

	public boolean find(String ndivalue);

	public void edit(Patient patient);

	public void delete(String ndivalue);

	public Patient select(String ndivalue);

	public String selectNdivalueById(int id);

	public List<Patient> selectAll();

}
