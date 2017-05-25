package com.medical.spring.model.json.utilities;

/**
 * This class is used to build Address field according to HL7-Fhir standard
 * Called in RegisterPacient.java and RegisterPersonal.java
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Rol {
	DataRol[] practitionerRole;

	/**
	 * @param practitionerRole
	 *            Array according to FHIR
	 * @see https://www.hl7.org/fhir/patient-definitions.html#Patient.address
	 * @see https://www.hl7.org/fhir/practitioner-definitions.html#Practitioner.address
	 */
	public void setDataRol(DataRol[] practitionerRole) {
		this.practitionerRole = practitionerRole;
	}
}
