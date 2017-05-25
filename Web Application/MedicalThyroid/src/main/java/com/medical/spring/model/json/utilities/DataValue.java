package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataValue {
	String value;
	String unit;
	String system;
	String code;

	public void setValue(String value) {
		this.value = value;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public void setCode(String code) {
		this.code = code;
	}
}