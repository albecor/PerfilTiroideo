package com.medical.spring;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.dao.PatientDao;
import com.medical.spring.model.Patient;

public class TestPatientDao {

	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PatientDao patientDao = (PatientDao) context.getBean("patientDao");

		Patient Carlos = new Patient();
		Carlos.setId(12);
		Carlos.setGiven("Carlos");
		Carlos.setFamily("Samper Claros");
		Carlos.setNdi("cedula");
		Carlos.setNdivalue("1");
		Carlos.setPassword("2525");
		Carlos.setGender("Masculino");
		Carlos.setBirthDate("15/10/2001");
		Carlos.setMaritalStatus("Casado");
		Carlos.setTelhome("8752525");
		Carlos.setTelmobile("8653698");
		Carlos.setTelwork("8749632");
		Carlos.setEmail("carlos@hotmail.com");
		Carlos.setLine("Colombia");
		Carlos.setCity("Neiva");
		Carlos.setGivenc("Sandra");
		Carlos.setFamilyc("Peralta");
		Carlos.setTelc("8719632");
		Carlos.setRelationship("Familia");
		Carlos.setManagingOrganization("Managin organization");
		Carlos.setBlood("O+");

		// add Carlos
		patientDao.insert(Carlos);

		// Call Carlos
		Patient patient = new Patient();
		patient = patientDao.select(Carlos.getNdivalue());
		System.out.println("call clara : " + patient.toString());

		// edit Carlos
		Carlos.setGiven("Carlos Mauricio");
		Carlos.setFamily("Iriarte Claros");
		patientDao.edit(Carlos);

		// Call Carlos to show modified content
		patient = new Patient();
		patient = patientDao.select(Carlos.getNdivalue());
		System.out.println("call Clara modified : " + patient.toString());
		System.out.println("" + patient.getMaritalStatus());

		// Maria
		Patient Maria = new Patient();
		Maria.setId(9);
		Maria.setGiven("Maria");
		Maria.setFamily("Lara Claros");
		Maria.setNdi("cedula");
		Maria.setNdivalue("2");
		Maria.setPassword("2525");
		Maria.setGender("Femenino");
		Maria.setBirthDate("15/05/2008");
		Maria.setMaritalStatus("Casada");
		Maria.setTelhome("8752525");
		Maria.setTelmobile("8653698");
		Maria.setTelwork("8749632");
		Maria.setEmail("maria@hotmail.com");
		Maria.setLine("Colombia");
		Maria.setCity("Neiva");
		Maria.setGivenc("Lola");
		Maria.setFamilyc("Mojica");
		Maria.setTelc("8719632");
		Maria.setRelationship("Pareja");
		Maria.setManagingOrganization("Managin organization");
		Maria.setBlood("O+");

		// add Maria to database
		patientDao.insert(Maria);

		// find all patient

		System.out.println("call all patient");
		List<Patient> list = patientDao.selectAll();
		if (list.isEmpty()) {
			System.out.println("no hay nada");
		}
		for (Patient patient2 : list) {
			System.out.println(patient2.toString());
		}

		((AnnotationConfigApplicationContext) context).close();
	}

}
