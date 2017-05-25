package com.medical.spring.resources;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Age {

	Period age;

	public Age(String birthDate) {

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaNac = LocalDate.parse(birthDate, fmt);
		LocalDate ahora = LocalDate.now();

		this.age = Period.between(fechaNac, ahora);
	}

	/**
	 * @return the age
	 */
	public Period getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Period age) {
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Age [age=" + age + "]";
	}

}
