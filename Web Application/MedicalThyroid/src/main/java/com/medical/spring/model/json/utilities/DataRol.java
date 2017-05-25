package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataRol {
	String managingOrganization;
	DataRole role;

	public void setMo(String managingOrganization) {
		this.managingOrganization = managingOrganization;
	}

	public void setRole(DataRole role) {
		this.role = role;
	}
}