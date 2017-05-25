package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Address field according to HL7-Fhir standard
 * Called in RegisterGlucose.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Range {
	DataRange[] referenceRange;

	/**
	 * @param referenceRange
	 *            Array according to FHIR
	 * @see https://www.hl7.org/fhir/observation-definitions.html#Observation.referenceRange
	 */
	public void setDataRange(DataRange[] referenceRange) {
		this.referenceRange = referenceRange;
	}
}
