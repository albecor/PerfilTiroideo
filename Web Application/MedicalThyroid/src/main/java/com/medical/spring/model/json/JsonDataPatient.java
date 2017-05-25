package com.medical.spring.model.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.medical.spring.model.Patient;
import com.medical.spring.model.json.utilities.Address;
import com.medical.spring.model.json.utilities.Communication;
import com.medical.spring.model.json.utilities.Contact;
import com.medical.spring.model.json.utilities.DataAddress;
import com.medical.spring.model.json.utilities.DataCoding;
import com.medical.spring.model.json.utilities.DataCodingContact;
import com.medical.spring.model.json.utilities.DataCommunication;
import com.medical.spring.model.json.utilities.DataContact;
import com.medical.spring.model.json.utilities.DataIdentifier;
import com.medical.spring.model.json.utilities.DataMarital;
import com.medical.spring.model.json.utilities.DataName;
import com.medical.spring.model.json.utilities.DataRelationship;
import com.medical.spring.model.json.utilities.DataTelecom;
import com.medical.spring.model.json.utilities.DataTelecomContact;
import com.medical.spring.model.json.utilities.Identifier;
import com.medical.spring.model.json.utilities.Language;
import com.medical.spring.model.json.utilities.LanguageCoding;
import com.medical.spring.model.json.utilities.Marital;
import com.medical.spring.model.json.utilities.Name;
import com.medical.spring.model.json.utilities.NameContact;
import com.medical.spring.model.json.utilities.Telecom;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class JsonDataPatient {

	private int id;
	private JsonElement identifier;
	private JsonElement name;
	private JsonElement telecom;
	private String gender;
	private String birthDate;
	private JsonElement address;
	private JsonElement maritalStatus;
	private JsonElement contact;
	private JsonElement communication;
	private String managingOrganization;
	private String password;
	private String token_id;
	private String bloodType;

	/**
	 * generate json of data patient
	 * @param patient
	 */
	public JsonDataPatient(Patient patient) {
		super();

		DataIdentifier dataIdentifier = new DataIdentifier();
		dataIdentifier.setUse("official");
		dataIdentifier.setSystem("urn:oid:2.16.840.1.113883.2.17.6.3");
		dataIdentifier.setValue(patient.getNdivalue());
		dataIdentifier.setDisplay(patient.getNdi());

		Identifier ID = new Identifier();
		ID.setDataIdentifier(new DataIdentifier[] { dataIdentifier });

		// JsonElement identifier
		JsonElement identifier = new Gson().toJsonTree(ID);
		this.identifier = identifier;
		// -----------------------

		List<String> lastname = new ArrayList<String>();
		lastname.add(patient.getFamily());

		List<String> dataname = new ArrayList<String>();
		dataname.add(patient.getGiven());

		DataName dataName = new DataName();
		dataName.setUse("official");
		dataName.setFamily(lastname);
		dataName.setGiven(dataname);

		Name NAME = new Name();
		NAME.setDataName(new DataName[] { dataName });

		// JsonElement name
		JsonElement name = new Gson().toJsonTree(NAME);
		this.name = name;
		// -----------------

		DataTelecom dataMobile = new DataTelecom();
		dataMobile.setSystem("phone");
		dataMobile.setValue(patient.getTelmobile());
		dataMobile.setUse("mobile");

		DataTelecom dataWork = new DataTelecom();
		dataWork.setSystemW("phone");
		dataWork.setValueW(patient.getTelwork());
		dataWork.setUseW("work");

		DataTelecom dataHome = new DataTelecom();
		dataHome.setSystemH("phone");
		dataHome.setValueH(patient.getTelhome());
		dataHome.setUseH("home");

		DataTelecom dataEmail = new DataTelecom();
		dataEmail.setSystemE("email");
		dataEmail.setValueE(patient.getEmail());
		dataEmail.setUseE("home");

		Telecom TELECOM = new Telecom();
		TELECOM.setDataTelecom(new DataTelecom[] { dataMobile, dataHome, dataWork, dataEmail });

		// JsonElement telecom
		JsonElement telecom = new Gson().toJsonTree(TELECOM);
		this.telecom = telecom;
		// ----------------------

		Address ADDRESS = new Address();
		DataAddress dataAddress = new DataAddress();
		dataAddress.setUse("home");
		List<String> lines = new ArrayList<String>();
		lines.add(patient.getLine());
		dataAddress.setLine(lines);
		dataAddress.setCity(patient.getCity());
		dataAddress.setCountry("COL");
		ADDRESS.setDataAddress(new DataAddress[] { dataAddress });

		// JsonElement address
		JsonElement address = new Gson().toJsonTree(ADDRESS);
		this.address = address;
		// ----------------------

		DataCoding dataCoding = new DataCoding();

		dataCoding.setSystem("http://hl7.org/fhir/v3/MaritalStatus");

		switch (patient.getMaritalStatus()) {
		case "Casado(a)":
			dataCoding.setCode("M");
			dataCoding.setDisplay("Married");
			break;
		case "Soltero(a)":
			dataCoding.setCode("U");
			dataCoding.setDisplay("Unmarried");
			break;
		case "Unión libre":
			dataCoding.setCode("T");
			dataCoding.setDisplay("Domestic partner");
			break;
		case "Viudo(a)":
			dataCoding.setCode("W");
			dataCoding.setDisplay("Widowed");
			break;
		case "Separado(a)":
			dataCoding.setCode("L");
			dataCoding.setDisplay("Legally Separated");
			break;
		}

		DataMarital dataMarital = new DataMarital();
		dataMarital.setDataAddress(new DataCoding[] { dataCoding });

		Marital MARITAL = new Marital();
		MARITAL.setMarital(dataMarital);

		// JsonElement maritalStatus
		JsonElement maritalStatus = new Gson().toJsonTree(MARITAL);
		this.maritalStatus = maritalStatus;
		// ----------------------

		DataCodingContact dataCodingContact = new DataCodingContact();

		// relationship to english
		switch (patient.getRelationship()) {
		case "Familia":
			dataCodingContact.setCode("family");
			break;
		case "Pareja":
			dataCodingContact.setCode("partner");
			break;
		case "Amigo(a)":
			dataCodingContact.setCode("friend");
			break;
		}

		dataCodingContact.setSystem("http://hl7.org/fhir/patient-contact-relationship");

		NameContact nameContact = new NameContact();
		nameContact.setUse("official");
		List<String> lastnamec = new ArrayList<String>();
		lastnamec.add(patient.getFamilyc());
		List<String> datanamec = new ArrayList<String>();
		datanamec.add(patient.getGivenc());
		nameContact.setFamily(lastnamec);
		nameContact.setGiven(datanamec);

		DataTelecomContact dataTelecomContact = new DataTelecomContact();
		dataTelecomContact.setSystem("phone");
		dataTelecomContact.setValue(patient.getTelc());
		dataTelecomContact.setUse("mobile");

		DataRelationship dataRelationship = new DataRelationship();
		dataRelationship.setDataContact(new DataCodingContact[] { dataCodingContact });

		DataContact dataContact = new DataContact();
		dataContact.setRelationship(new DataRelationship[] { dataRelationship });
		dataContact.setNameContact(nameContact);
		dataContact.setTelecomContact(new DataTelecomContact[] { dataTelecomContact });

		Contact CONTACT = new Contact();
		CONTACT.setDataContact(new DataContact[] { dataContact });

		// JsonElement maritalStatus
		JsonElement contact = new Gson().toJsonTree(CONTACT);
		this.contact = contact;
		// ----------------------

		Language language = new Language();
		language.setText("Español Latino");

		LanguageCoding languageCoding = new LanguageCoding();
		languageCoding.setSystem("urn:ietf:bcp:47");
		languageCoding.setCode("es-419");
		languageCoding.setDisplay("Español");
		language.setLanguageCoding(new LanguageCoding[] { languageCoding });

		DataCommunication dataCommunication = new DataCommunication();
		dataCommunication.setPreferred(Boolean.TRUE);
		dataCommunication.setLanguage(language);

		Communication COM = new Communication();
		COM.setDataCommunication(new DataCommunication[] { dataCommunication });

		// JsonElement maritalStatus
		JsonElement communication = new Gson().toJsonTree(COM);
		this.communication = communication;
		// ----------------------

		switch (patient.getGender()) {
		case "Hombre":
			this.gender = "male";
			break;
		case "Mujer":
			this.gender = "female";
			break;
		case "Otro":
			this.gender = "other";
			break;

		}

		this.managingOrganization = patient.getManagingOrganization();
		this.password = patient.getPassword();
		this.token_id = "";
		this.bloodType = patient.getBlood();
		this.id = patient.getId();

		this.birthDate = patient.getBirthDate();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the identifier
	 */
	public JsonElement getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(JsonElement identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the name
	 */
	public JsonElement getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(JsonElement name) {
		this.name = name;
	}

	/**
	 * @return the telecom
	 */
	public JsonElement getTelecom() {
		return telecom;
	}

	/**
	 * @param telecom
	 *            the telecom to set
	 */
	public void setTelecom(JsonElement telecom) {
		this.telecom = telecom;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate
	 *            the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the address
	 */
	public JsonElement getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(JsonElement address) {
		this.address = address;
	}

	/**
	 * @return the maritalStatus
	 */
	public JsonElement getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(JsonElement maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the contact
	 */
	public JsonElement getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(JsonElement contact) {
		this.contact = contact;
	}

	/**
	 * @return the communication
	 */
	public JsonElement getCommunication() {
		return communication;
	}

	/**
	 * @param communication
	 *            the communication to set
	 */
	public void setCommunication(JsonElement communication) {
		this.communication = communication;
	}

	/**
	 * @return the managingOrganization
	 */
	public String getManagingOrganization() {
		return managingOrganization;
	}

	/**
	 * @param managingOrganization
	 *            the managingOrganization to set
	 */
	public void setManagingOrganization(String managingOrganization) {
		this.managingOrganization = managingOrganization;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the token_id
	 */
	public String getToken_id() {
		return token_id;
	}

	/**
	 * @param token_id
	 *            the token_id to set
	 */
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}

	/**
	 * @return the bloodType
	 */
	public String getBloodType() {
		return bloodType;
	}

	/**
	 * @param bloodType
	 *            the bloodType to set
	 */
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonDataPatient [id=" + id + ", identifier=" + identifier + ", name=" + name + ", telecom=" + telecom
				+ ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address + ", maritalStatus="
				+ maritalStatus + ", contact=" + contact + ", communication=" + communication
				+ ", managingOrganization=" + managingOrganization + ", password=" + password + ", token_id=" + token_id
				+ ", bloodType=" + bloodType + "]";
	}

}
