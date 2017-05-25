
package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Subject field according to HL7-Fhir standard
 * Called in RegisterGlucose.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Subject {
	DataSubject subject;

	/**
	 * @param subject
	 *            Array according to FHIR
	 * @see https://www.hl7.org/fhir/observation-definitions.html#Observation.subject
	 */
	public void setDataSubject(DataSubject subject) {
		this.subject = subject;
	}
}
