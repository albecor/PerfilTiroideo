package com.medical.spring;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.model.Admin;
import com.medical.spring.services.AdminService;

public class TestAdminDao {

	public static void main(String args[]) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		AdminService adminService = (AdminService) context.getBean("adminService");

		Admin lina = new Admin();
		lina.setId(8);
		lina.setNdi("Ndi");
		lina.setNdivalue("25");
		lina.setName("lina");
		lina.setPhone("8635907");
		lina.setPassword("852");
		lina.setEmail("aop@hotmail.com");
		lina.setAddress("Calle 45c 18 - 56");

		Admin andres = new Admin();
		andres.setId(2);
		andres.setNdi("Ndi");
		andres.setNdivalue("13456");
		andres.setName("andres");
		andres.setPhone("8635907");
		andres.setPassword("123456789");
		andres.setEmail("aop@hotmail.com");
		andres.setAddress("Calle 45c 18 - 56");

		Admin lola = new Admin();
		lola.setId(3);
		lola.setNdi("Ndi");
		lola.setNdivalue("13456");
		lola.setName("lola");
		lola.setPhone("8635907");
		lola.setPassword("123456789");
		lola.setEmail("aop@hotmail.com");
		lola.setAddress("Calle 45c 18 - 56");

		adminService.insert(lina);
		adminService.insert(andres);
		adminService.insert(lola);

		System.out.println("Find All");
		List<Admin> admins = adminService.selectAll();
		for (Admin admin : admins) {
			System.out.println(admin);
		}

		System.out.println("Delete person Id = 3");
		adminService.delete(lola.getNdivalue());

		lina.setAddress("Update direccion");
		lina.setName("Update Nombre");

	
		adminService.edit(lina);

		System.out.println("Find person Id = 2");
		Admin admin = adminService.select("13456");
		System.out.println(admin);

		

		System.out.println("Find All Again");
		admins = adminService.selectAll();
		System.out.println("" + admins.size());
		for (Admin p : admins) {
			System.out.println(p);
		}

		System.out.println(adminService.selectById(1));
		context.close();
	}

}