package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class Language {
	String text;
	LanguageCoding[] coding;

	public void setText(String text) {
		this.text = text;
	}

	public void setLanguageCoding(LanguageCoding[] coding) {
		this.coding = coding;
	}
}