package com.medical.spring.model.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.medical.spring.model.Personal;
import com.medical.spring.model.json.utilities.Address;
import com.medical.spring.model.json.utilities.DataAddress;
import com.medical.spring.model.json.utilities.DataCoding;
import com.medical.spring.model.json.utilities.DataIdentifier;
import com.medical.spring.model.json.utilities.DataName;
import com.medical.spring.model.json.utilities.DataRol;
import com.medical.spring.model.json.utilities.DataRole;
import com.medical.spring.model.json.utilities.DataTelecom;
import com.medical.spring.model.json.utilities.Identifier;
import com.medical.spring.model.json.utilities.Name;
import com.medical.spring.model.json.utilities.Rol;
import com.medical.spring.model.json.utilities.Telecom;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class JsonDataPersonal {

	private int id;
	private JsonElement identifier;
	private String password;
	private JsonElement name;
	private JsonElement telecom;
	private String gender;
	private String birthDate;
	private JsonElement address;
	private JsonElement practitionerRole;
	private String token_id;

	/**
	 * generate json of data personal
	 * @param personal
	 */
	public JsonDataPersonal(Personal personal) {
		super();

		DataIdentifier dataIdentifier = new DataIdentifier();
		dataIdentifier.setUse("official");
		dataIdentifier.setSystem("urn:oid:2.16.840.1.113883.2.17.6.3");
		dataIdentifier.setValue(personal.getNdivalue());
		dataIdentifier.setDisplay(personal.getNdi());

		Identifier ID = new Identifier();
		ID.setDataIdentifier(new DataIdentifier[] { dataIdentifier });

		// JsonElement identifier
		JsonElement identifier = new Gson().toJsonTree(ID);
		this.identifier = identifier;
		// -----------------------

		List<String> lastname = new ArrayList<String>();
		lastname.add(personal.getFamily());

		List<String> dataname = new ArrayList<String>();
		dataname.add(personal.getGiven());

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
		dataMobile.setValue(personal.getTelmobile());
		dataMobile.setUse("mobile");

		DataTelecom dataWork = new DataTelecom();
		dataWork.setSystemW("phone");
		dataWork.setValueW(personal.getTelwork());
		dataWork.setUseW("work");

		DataTelecom dataEmail = new DataTelecom();
		dataEmail.setSystemE("email");
		dataEmail.setValueE(personal.getEmail());
		dataEmail.setUseE("home");

		Telecom TELECOM = new Telecom();
		if (personal.getTelwork().isEmpty() && personal.getEmail().isEmpty()) {
			// telecom = "["+mobile+"]";
			TELECOM.setDataTelecom(new DataTelecom[] { dataMobile });
		} else if (!personal.getTelwork().isEmpty() && personal.getEmail().isEmpty()) {
			// telecom = "["+mobile+","+work+"]";
			TELECOM.setDataTelecom(new DataTelecom[] { dataMobile, dataWork });
		} else if (personal.getTelwork().isEmpty() && !personal.getEmail().isEmpty()) {
			// telecom = "["+mobile+","+email+"]";
			TELECOM.setDataTelecom(new DataTelecom[] { dataMobile, dataEmail });
		} else {
			// telecom = "["+mobile+","+work+","+email+"]";
			TELECOM.setDataTelecom(new DataTelecom[] { dataMobile, dataWork, dataEmail });
		}

		// JsonElement telecom
		JsonElement telecom = new Gson().toJsonTree(TELECOM);
		this.telecom = telecom;
		// ----------------------

		Address ADDRESS = new Address();
		DataAddress dataAddress = new DataAddress();
		dataAddress.setUse("home");
		List<String> lines = new ArrayList<>();
		lines.add(personal.getLine());
		dataAddress.setLine(lines);
		dataAddress.setCity(personal.getCity());
		dataAddress.setCountry("COL");
		ADDRESS.setDataAddress(new DataAddress[] { dataAddress });

		// JsonElement address
		JsonElement address = new Gson().toJsonTree(ADDRESS);
		this.address = address;
		// ----------------------

		String displaym = "";
		String codeRole = "";

		switch (personal.getRole()) {
		case "Médico(a) Endocrinólogo(a)":
			displaym = "Endocrinologist";
			codeRole = "61894003";
			break;
		case "Médico(a) General":
			displaym = "General Doctor";
			codeRole = "59058001";
			break;
		case "Estudiante de medicina":
			displaym = "Medical Student";
			codeRole = "398130009";
			break;
		case "Enfermero(a) Jefe":
			displaym = "Nurse Manager";
			codeRole = "309446002";
			break;
		case "Enfermero(a)":
			displaym = "Endocrinologist";
			codeRole = "General Nurse";
			break;
		}

		DataCoding dataCoding = new DataCoding();
		dataCoding.setCode(codeRole);
		dataCoding.setSystem("http://snomed.info/sct");
		dataCoding.setDisplay(displaym);

		DataRole dataRole = new DataRole();
		dataRole.setDataCod(new DataCoding[] { dataCoding });

		DataRol dataRol = new DataRol();
		dataRol.setMo(personal.getManagingOrganization());
		dataRol.setRole(dataRole);

		Rol ROL = new Rol();
		ROL.setDataRol(new DataRol[] { dataRol });

		// JsonElement practitionerRole
		JsonElement practitionerRole = new Gson().toJsonTree(ROL);
		this.practitionerRole = practitionerRole;
		// ---------------------------

		switch (personal.getGender()) {
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

		this.password = personal.getPassword();

		this.birthDate = personal.getBirthDate();
		// this.token_id = "856";
		this.id = personal.getId();
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
	 * @return the practitionerRole
	 */
	public JsonElement getPractitionerRole() {
		return practitionerRole;
	}

	/**
	 * @param practitionerRole
	 *            the practitionerRole to set
	 */
	public void setPractitionerRole(JsonElement practitionerRole) {
		this.practitionerRole = practitionerRole;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonDataPersonal [id=" + id + ", identifier=" + identifier + ", password=" + password + ", name=" + name
				+ ", telecom=" + telecom + ", gender=" + gender + ", birthDate=" + birthDate + ", address=" + address
				+ ", practitionerRole=" + practitionerRole + ", token_id=" + token_id + "]";
	}

}
