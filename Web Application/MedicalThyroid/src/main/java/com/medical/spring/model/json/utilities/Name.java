package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Name field according to HL7-Fhir standard Called
 * in RegisterPacient.java and RegisterPersonal.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Name {
	DataName[] name;

	/**
	 * @param name
	 *            Array acording to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.name
	 * @see https://www.hl7.org/fhir/practitioner-definitions.html#Practitioner.name
	 */
	public void setDataName(DataName[] name) {
		this.name = name;
	}
}
