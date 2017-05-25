package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Marital field according to HL7-Fhir standard
 * Called in RegisterPacient.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Marital {
	DataMarital maritalStatus;

	/**
	 * @param maritalStatus
	 *            Array acording to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.maritalStatus
	 */
	public void setMarital(DataMarital maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
}
