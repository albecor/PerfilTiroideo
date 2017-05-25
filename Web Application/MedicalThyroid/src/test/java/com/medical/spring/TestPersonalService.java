package com.medical.spring;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.model.Personal;
import com.medical.spring.services.PersonalService;

public class TestPersonalService {

	public static void main(String args[]) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		PersonalService personalService = (PersonalService) context.getBean("personalService");

		Personal clara = new Personal();
		clara.setId(5);
		clara.setGiven("Clara");
		clara.setFamily("Gomez");
		clara.setPassword("8525");
		clara.setNdi("cedula");
		clara.setNdivalue("5");
		clara.setGender("Femenino");
		clara.setBirthDate("25/12/2008");
		clara.setTelmobile("3102589636");
		clara.setTelwork("8756936");
		clara.setEmail("magnolia@hotmail.com");
		clara.setLine("Colombia");
		clara.setCity("Neiva");
		clara.setManagingOrganization("Manager ORganizacion");
		clara.setRole("852");

		// Add clara into database
		personalService.insert(clara);

		// Call clara
		Personal personal = new Personal();
		personal = personalService.select(clara.getNdivalue());
		System.out.println("call clara : " + personal.toString());

		// edit clara
		clara.setFamily("Hernandez");
		clara.setGiven("Clara Ines");
		personalService.edit(clara);

		// Call clara to show modified content
		personal = new Personal();
		personal = personalService.select(clara.getNdivalue());
		System.out.println("call Clara modified : " + personal.toString());

		// Add sonia
		Personal sonia = new Personal();
		sonia.setId(1);
		sonia.setGiven("Sonia");
		sonia.setFamily("Godinez");
		sonia.setPassword("6985");
		sonia.setNdi("cedula");
		sonia.setNdivalue("1");
		sonia.setGender("Femenino");
		sonia.setBirthDate("12/10/201");
		sonia.setTelmobile("3158569635");
		sonia.setTelwork("8625896");
		sonia.setEmail("sonia@hotmail.com");
		sonia.setLine("Colombia");
		sonia.setCity("Neiva");
		sonia.setManagingOrganization("Manager ORganizacion");
		sonia.setRole("93");
		personalService.insert(sonia);

		// find all personal
		System.out.println("call all personal");
		List<Personal> list = personalService.selectAll();

		for (Personal personal2 : list) {
			System.out.println(personal2.toString());
		}

		((AnnotationConfigApplicationContext) context).close();

	}

}