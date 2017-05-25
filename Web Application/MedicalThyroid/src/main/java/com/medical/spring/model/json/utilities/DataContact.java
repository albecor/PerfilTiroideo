package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataContact {
	DataRelationship[] relationship;
	DataTelecomContact[] telecom;
	NameContact name;

	public void setRelationship(DataRelationship[] relationship) {
		this.relationship = relationship;
	}

	public void setNameContact(NameContact name) {
		this.name = name;
	}

	public void setTelecomContact(DataTelecomContact[] telecom) {
		this.telecom = telecom;
	}
}
