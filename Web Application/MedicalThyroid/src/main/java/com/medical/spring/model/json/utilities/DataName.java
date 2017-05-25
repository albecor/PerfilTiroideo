package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
import java.util.List;

public class DataName {
	String use;
	List<String> family;
	List<String> given;

	public void setUse(String use) {
		this.use = use;
	}

	public void setFamily(List<String> family) {
		this.family = family;
	}

	public void setGiven(List<String> given) {
		this.given = given;
	}
}