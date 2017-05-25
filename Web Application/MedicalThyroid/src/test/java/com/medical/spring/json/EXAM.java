package com.medical.spring.json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.medical.spring.config.ApplicationConfig;
import com.medical.spring.dao.ExamDao;
import com.medical.spring.model.Exam;

public class EXAM {
	public static void main(String args[]) throws SQLException, IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		// ExamDao examDao = context.getBean(ExamDao.class);
		ExamDao examDao = (ExamDao) context.getBean("examDao");

		Exam exam = new Exam();
		exam.setCode("785-9");
		exam.setSystemCode("www");
		exam.setDisplayCode("ALP");
		exam.setValueQuantity("25");
		exam.setUnit("U/L");
		exam.setHigh("50");
		exam.setLow("0");
		exam.setReferenceSubject("2");
		exam.setDisplaySubject("Maria dias");
		exam.setComments("Este es un comentario");
		exam.setIssued("45");
		exam.setReferencePerfomer("14");
		exam.setDisplayPerformer("amanito godines");

		examDao.insert(exam);

		System.out.println("call all patient");
		List<Exam> list = examDao.selectAll("2");
		if (list.isEmpty()) {
			System.out.println("no hay nada");
		}

		for (Exam patient2 : list) {
			System.out.println(patient2.toString());
		}

		((AnnotationConfigApplicationContext) context).close();

	}

}