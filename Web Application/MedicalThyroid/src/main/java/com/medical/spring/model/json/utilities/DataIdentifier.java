package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataIdentifier {
	String use;
	String system;
	String value;
	String display;
	String password;

	public void setUse(String use) {
		this.use = use;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
