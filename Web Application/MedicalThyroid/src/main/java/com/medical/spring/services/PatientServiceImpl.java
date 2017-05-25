package com.medical.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.spring.dao.PatientDao;
import com.medical.spring.model.Patient;
import com.medical.spring.model.User;

@Service("patientService")
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientDao patientDao;

	@Autowired
	private UserService userService;

	@Override
	public void insert(Patient patient) {

		User user = new User();
		user.setUsername(patient.getNdivalue());
		user.setPassword(patient.getPassword());
		user.setPatient(true);
		userService.insert(user);

		patientDao.insert(patient);
	}

	@Override
	public void edit(Patient patient) {
		userService.edit(patient.getNdivalue(), patient.getPassword(), patientDao.selectNdivalueById(patient.getId()));
		patientDao.edit(patient);
	}

	@Override
	public void delete(String ndivalue) {
		userService.delete(ndivalue, "ROLE_PATIENT");
		patientDao.delete(ndivalue);
	}

	@Override
	public Patient select(String ndivalue) {
		return patientDao.select(ndivalue);
	}

	@Override
	public List<Patient> selectAll() {
		return patientDao.selectAll();
	}

	@Override
	public boolean find(String ndivalue) {
		return patientDao.find(ndivalue);
	}

	public String selectNdivalueById(int id) {
		return patientDao.selectNdivalueById(id);
	}

}
