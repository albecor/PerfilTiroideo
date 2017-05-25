package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Communication field according to HL7-Fhir
 * standard Called in RegisterPacient.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Communication {
	DataCommunication[] communication;

	/**
	 * @param communication
	 *            Array acording to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.communication
	 */
	public void setDataCommunication(DataCommunication[] communication) {
		this.communication = communication;
	}
}
