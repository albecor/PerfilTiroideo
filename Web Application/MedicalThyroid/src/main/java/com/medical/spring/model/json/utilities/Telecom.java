package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Telecom field according to HL7-Fhir standard
 * Called in RegisterPacient.java and RegisterPersonal.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Telecom {
	DataTelecom[] telecom;

	/**
	 * @param telecom
	 *            Array according to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.telecom
	 * @see https://www.hl7.org/fhir/practitioner-definitions.html#Practitioner.telecom
	 */
	public void setDataTelecom(DataTelecom[] telecom) {
		this.telecom = telecom;
	}
}
