package com.medical.spring;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.dao.LabDao;
import com.medical.spring.model.Lab;

public class TestLabDao {

	public static void main(String args[]) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		LabDao labService = (LabDao) context.getBean("labDao");

		Lab lina = new Lab();
		lina.setNdi("Ndi");
		lina.setNdivalue("12");
		lina.setName("lina");
		lina.setPhone("8635907");
		lina.setPassword("852");
		lina.setEmail("aop@hotmail.com");
		lina.setAddress("Calle 45c 18 - 56");

		Lab lola = new Lab();
		lola.setNdi("Ndi");
		lola.setNdivalue("15");
		lola.setName("lola");
		lola.setPhone("8635907");
		lola.setPassword("123456789");
		lola.setEmail("aop@hotmail.com");
		lola.setAddress("Calle 45c 18 - 56");

		Lab andres = new Lab();
		andres.setNdi("Ndi");
		andres.setNdivalue("15");
		andres.setName("andres");
		andres.setPhone("8635907");
		andres.setPassword("123456789");
		andres.setEmail("aop@hotmail.com");
		andres.setAddress("Calle 45c 18 - 56");

		labService.insert(lina);
		labService.insert(lola);
		labService.insert(andres);

		System.out.println("Find All");
		List<Lab> admins = labService.selectAll();
		for (Lab admin : admins) {
			System.out.println(admin);
		}

		System.out.println("Delete person Id = 3");
		labService.delete(andres.getNdivalue());

		lina = labService.select(lina.getNdivalue());
		lina.setAddress("Update direccion");
		lina.setName("Update Nombre");

		labService.edit(lina);

		System.out.println("Find All");
		admins = labService.selectAll();
		for (Lab admin : admins) {
			System.out.println(admin);
		}

		System.out.println("Find All Again");
		admins = labService.selectAll();

		for (Lab p : admins) {
			System.out.println(p);
		}

		labService.delete(lina.getNdivalue());
		labService.delete(lola.getNdivalue());

		context.close();
	}

}