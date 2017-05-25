package com.medical.spring.model.json.utilities;

import java.util.List;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataAddress {
	String use;
	String city;
	String country;
	List<String> line;

	public void setUse(String use) {
		this.use = use;
	}

	public void setLine(List<String> line) {
		this.line = line;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
