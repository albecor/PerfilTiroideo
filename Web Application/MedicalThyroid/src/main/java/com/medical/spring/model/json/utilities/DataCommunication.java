package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataCommunication {
	Boolean preferred;
	Language language;

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
}