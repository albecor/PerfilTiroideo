package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Identifier field according to HL7-Fhir standard
 * Called in RegisterPacient.java, RegisterPacient.java and RegisterGlucose.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Identifier {
	DataIdentifier[] identifier;

	/**
	 * @param identifier
	 *            Array acording to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.identifier
	 * @see https://www.hl7.org/fhir/practitioner-definitions.html#Practitioner.identifier
	 * @see https://www.hl7.org/fhir/observation-definitions.html#Observation.identifier
	 */
	public void setDataIdentifier(DataIdentifier[] identifier) {
		this.identifier = identifier;
	}
}