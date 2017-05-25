package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Value field according to HL7-Fhir standard Called
 * in and RegisterGlucose.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Value {
	DataValue valueQuantity;

	/**
	 * @param valueQuantity
	 *            Array according to FHIR
	 * @see https://www.hl7.org/fhir/observation-definitions.html#Observation.value_x_
	 */
	public void setDataValue(DataValue valueQuantity) {
		this.valueQuantity = valueQuantity;
	}
}
