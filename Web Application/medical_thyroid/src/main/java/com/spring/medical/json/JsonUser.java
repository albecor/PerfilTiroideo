package com.spring.medical.json;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spring.medical.model.User;

/**
 * 
 * create Json for user object
 *
 */
public class JsonUser {

	private Integer id;
	private String password;
	private String identifier;
	private String name;
	private String telecom;
	private String gender;
	private String birthdate;
	private String address;
	private String maritalStatus;
	private String contact;
	private String communication;
	private String managingOrganization;
	private String bloodtype;
	private String practitionerRole;

	public JsonUser(User user) {
		this.id = user.getId();
		this.password = user.getPassword();
		setIdentifier(user.getNdivalue(), user.getNdi());
		setName(user.getGiven(), user.getFamily());
		setTelecom(user.getTelmobile(), user.getTelhome(), user.getTelwork(), user.getEmail());
		this.gender = user.getGender();
		this.birthdate = user.getBirthDate();
		setAddress(user.getCity(), user.getLine());
		setMaritalStatus(user.getMaritalStatus());
		setContact(user.getRelationship(), user.getGivenc(), user.getFamilyc(), user.getTelc());
		setCommunication();
		this.managingOrganization = user.getManagingOrganization();
		this.bloodtype = user.getBloodtype();
		setPractitionerRole(user.getManagingOrganization(), user.getPractitionerRole());
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
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String value, String display) {

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode identifier = factory.objectNode();
		ArrayNode arrayNode = factory.arrayNode();

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("use", "official");
		objectNode.put("value", value);
		objectNode.put("system", "urn:oid:2.16.840.1.113883.2.17.6.3");
		objectNode.put("display", display);

		arrayNode.add(objectNode);

		identifier.set("identifier", arrayNode);
		this.identifier = identifier.toString();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String given, String family) {

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode name = factory.objectNode();
		ArrayNode arrayNode = factory.arrayNode();

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("use", "official");
		objectNode.set("given", new ArrayNode(new JsonNodeFactory(false)).add(given));
		objectNode.set("family", new ArrayNode(new JsonNodeFactory(false)).add(family));
		arrayNode.add(objectNode);

		name.set("name", arrayNode);
		this.name = name.toString();
	}

	/**
	 * @return the telecom
	 */
	public String getTelecom() {
		return telecom;
	}

	/**
	 * @param telecom
	 *            the telecom to set
	 */
	public void setTelecom(String telMobile, String telHome, String telWork, String email) {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode telecom = factory.objectNode();
		ArrayNode arrayNode = factory.arrayNode();

		ObjectNode mobile = factory.objectNode();
		mobile.put("use", "mobile");
		mobile.put("value", telMobile);
		mobile.put("system", "phone");
		arrayNode.add(mobile);

		ObjectNode home = factory.objectNode();
		home.put("use", "home");
		home.put("value", telHome);
		home.put("system", "phone");
		arrayNode.add(home);

		ObjectNode work = factory.objectNode();
		work.put("use", "work");
		work.put("value", telWork);
		work.put("system", "phone");
		arrayNode.add(work);

		ObjectNode email1 = factory.objectNode();
		email1.put("use", "home");
		email1.put("value", email);
		email1.put("system", "email");
		arrayNode.add(email1);

		telecom.set("telecom", arrayNode);

		this.telecom = telecom.toString();
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
	 * @return the birthdate
	 */
	public String getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String city, String line) {

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode address = factory.objectNode();
		ArrayNode arrayNode = factory.arrayNode();

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("use", "home");
		objectNode.put("city", city);
		objectNode.set("line", new ArrayNode(new JsonNodeFactory(false)).add(line));
		objectNode.put("country", "COL");
		arrayNode.add(objectNode);

		address.set("address", arrayNode);

		this.address = address.toString();
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String code) {

		String display = "";

		switch (code) {
		case "A":
			display = "Annulled";
			break;
		case "D":
			display = "Divorced";
			break;
		case "I":
			display = "Interlocutory";
			break;
		case "L":
			display = "Legally Separated";
			break;
		case "M":
			display = "Married";
			break;
		case "P":
			display = "Polygamous";
			break;
		case "S":
			display = "Never Married";
			break;
		case "T":
			display = "Domestic partner";
			break;
		case "U":
			display = "Unmarried";
			break;
		case "W":
			display = "Widowed";
			break;
		case "UNK":
			display = "Unknown";
			break;
		}

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode maritalStatus = factory.objectNode();
		ObjectNode coding = factory.objectNode();
		ArrayNode arrayNode = factory.arrayNode();

		ObjectNode objectNode = factory.objectNode();
		objectNode.put("code", code);
		objectNode.put("system", "http://hl7.org/fhir/v3/MaritalStatus");
		objectNode.put("display", display);
		arrayNode.add(objectNode);

		coding.set("coding", arrayNode);

		maritalStatus.set("maritalStatus", coding);

		this.maritalStatus = maritalStatus.toString();
	}

	/**
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            the contact to set
	 */
	public void setContact(String code, String given, String family, String phone) {

		JsonNodeFactory factory = JsonNodeFactory.instance;

		ObjectNode contact = factory.objectNode();

		ArrayNode arrayCode = factory.arrayNode();
		arrayCode.add(new ObjectNode(new JsonNodeFactory(false))
				.put("system", "http://hl7.org/fhir/patient-contact-relationship").put("code", code));

		contact.set("relationship", new ArrayNode(new JsonNodeFactory(false))
				.add(new ObjectNode(new JsonNodeFactory(false)).set("coding", arrayCode)));

		ArrayNode telecom = factory.arrayNode();
		telecom.add(new ObjectNode(new JsonNodeFactory(false)).put("use", "mobile").put("value", phone).put("system",
				"phone"));

		contact.set("telecom", telecom);

		ObjectNode objectName = factory.objectNode();
		objectName.put("use", "official");
		objectName.set("given", new ArrayNode(new JsonNodeFactory(false)).add(given));
		objectName.set("family", new ArrayNode(new JsonNodeFactory(false)).add(family));
		contact.set("name", objectName);

		ObjectNode contact1 = factory.objectNode();
		contact1.set("contact", new ArrayNode(new JsonNodeFactory(false)).add(contact));

		this.contact = contact1.toString();
	}

	/**
	 * @return the communication
	 */
	public String getCommunication() {
		return communication;
	}

	/**
	 * @param communication
	 *            the communication to set
	 */
	public void setCommunication() {
		JsonNodeFactory factory = JsonNodeFactory.instance;

		ArrayNode arrayCode = factory.arrayNode();
		arrayCode.add(new ObjectNode(new JsonNodeFactory(false)).put("code", "es-419").put("system", "urn:ietf:bcp:47")
				.put("display", "Español"));

		ArrayNode telecom = factory.arrayNode();
		telecom.add(new ObjectNode(new JsonNodeFactory(false)).put("use", "mobile").put("value", "valuetelcom")
				.put("system", "phone"));

		ObjectNode language = factory.objectNode();
		language.put("text", "Español latino");
		language.set("coding", arrayCode);

		ObjectNode communication1 = factory.objectNode();
		communication1.set("language", language);
		communication1.put("preferred", true);

		ObjectNode communication = factory.objectNode();
		communication.set("communication", new ArrayNode(new JsonNodeFactory(false)).add(communication1));
		this.communication = communication.toString();
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
	 * @return the bloodtype
	 */
	public String getBloodtype() {
		return bloodtype;
	}

	/**
	 * @param bloodtype
	 *            the bloodtype to set
	 */
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	/**
	 * @return the practitionerRole
	 */
	public String getPractitionerRole() {
		return practitionerRole;
	}

	/**
	 * @param practitionerRole
	 *            the practitionerRole to set
	 */
	public void setPractitionerRole(String managingOrganization, String code) {

		String display = "";
		switch (code) {
		case "doctor":
			display = "Doctor";
			break;
		case "nurse":
			display = "Nurse";
			break;
		case "pharmacist":
			display = "Pharmacist";
			break;
		case "researcher":
			display = "Researcher";
			break;
		case "teacher":
			display = "Teacher/educator";
			break;
		case "ict":
			display = "ICT professional";
			break;
		}

		JsonNodeFactory factory = JsonNodeFactory.instance;

		ArrayNode arrayCode = factory.arrayNode();
		arrayCode.add(new ObjectNode(new JsonNodeFactory(false)).put("code", code)
				.put("system", "http://snomed.info/sct").put("display", display));

		ObjectNode role = factory.objectNode();
		role.set("coding", arrayCode);

		ObjectNode communication1 = factory.objectNode();
		communication1.set("role", role);
		communication1.put("managingOrganization", managingOrganization);

		ObjectNode practitionerRole = factory.objectNode();
		practitionerRole.set("practitionerRole", new ArrayNode(new JsonNodeFactory(false)).add(communication1));
		this.practitionerRole = practitionerRole.toString();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonUser [id=" + id + ", password=" + password + ", identifier=" + identifier + ", name=" + name
				+ ", telecom=" + telecom + ", gender=" + gender + ", birthdate=" + birthdate + ", address=" + address
				+ ", maritalStatus=" + maritalStatus + ", contact=" + contact + ", communication=" + communication
				+ ", managingOrganization=" + managingOrganization + ", bloodtype=" + bloodtype + ", practitionerRole="
				+ practitionerRole + "]";
	}
}
