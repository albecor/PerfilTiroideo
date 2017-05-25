package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Contact field according to HL7-Fhir standard
 * Called in RegisterPacient.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Contact {
	DataContact[] contact;

	/**
	 * @param contact
	 *            Array acording to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.contact
	 */
	public void setDataContact(DataContact[] contact) {
		this.contact = contact;
	}
}
