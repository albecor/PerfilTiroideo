package com.spring.medical.model;

import java.util.HashSet;
import java.util.Set;

/**
 * user class
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class User {

	private String given;
	private String family;
	private String password;
	private String ndi;
	private String ndivalue;
	private String gender;
	private String birthDate;
	private String maritalStatus;
	private String telhome;
	private String telmobile;
	private String telwork;
	private String email;
	private String line;
	private String city;
	private String givenc;
	private String familyc;
	private String telc;
	private String relationship;
	private String managingOrganization;
	private String bloodtype;
	private Integer id;
	private String practitionerRole;

	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Exam> exams = new HashSet<Exam>(0);
	private Set<Organization> labs = new HashSet<Organization>(0);

	/**
	 * @return the given
	 */
	public String getGiven() {
		return given;
	}

	/**
	 * @return the labs
	 */
	public Set<Organization> getLabs() {
		return labs;
	}

	/**
	 * @param labs
	 *            the labs to set
	 */
	public void setLabs(Set<Organization> labs) {
		this.labs = labs;
	}

	/**
	 * @param given
	 *            the given to set
	 */
	public void setGiven(String given) {
		this.given = given;
	}

	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}

	/**
	 * @param family
	 *            the family to set
	 */
	public void setFamily(String family) {
		this.family = family;
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
	 * @return the ndi
	 */
	public String getNdi() {
		return ndi;
	}

	/**
	 * @param ndi
	 *            the ndi to set
	 */
	public void setNdi(String ndi) {
		this.ndi = ndi;
	}

	/**
	 * @return the ndivalue
	 */
	public String getNdivalue() {
		return ndivalue;
	}

	/**
	 * @param ndivalue
	 *            the ndivalue to set
	 */
	public void setNdivalue(String ndivalue) {
		this.ndivalue = ndivalue;
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
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return the telhome
	 */
	public String getTelhome() {
		return telhome;
	}

	/**
	 * @param telhome
	 *            the telhome to set
	 */
	public void setTelhome(String telhome) {
		this.telhome = telhome;
	}

	/**
	 * @return the telmobile
	 */
	public String getTelmobile() {
		return telmobile;
	}

	/**
	 * @param telmobile
	 *            the telmobile to set
	 */
	public void setTelmobile(String telmobile) {
		this.telmobile = telmobile;
	}

	/**
	 * @return the telwork
	 */
	public String getTelwork() {
		return telwork;
	}

	/**
	 * @param telwork
	 *            the telwork to set
	 */
	public void setTelwork(String telwork) {
		this.telwork = telwork;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the line
	 */
	public String getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(String line) {
		this.line = line;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the givenc
	 */
	public String getGivenc() {
		return givenc;
	}

	/**
	 * @param givenc
	 *            the givenc to set
	 */
	public void setGivenc(String givenc) {
		this.givenc = givenc;
	}

	/**
	 * @return the familyc
	 */
	public String getFamilyc() {
		return familyc;
	}

	/**
	 * @param familyc
	 *            the familyc to set
	 */
	public void setFamilyc(String familyc) {
		this.familyc = familyc;
	}

	/**
	 * @return the telc
	 */
	public String getTelc() {
		return telc;
	}

	/**
	 * @param telc
	 *            the telc to set
	 */
	public void setTelc(String telc) {
		this.telc = telc;
	}

	/**
	 * @return the relationship
	 */
	public String getRelationship() {
		return relationship;
	}

	/**
	 * @param relationship
	 *            the relationship to set
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
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
	 * @return the blood
	 */
	public String getBloodtype() {
		return bloodtype;
	}

	/**
	 * @param blood
	 *            the blood to set
	 */
	public void setBloodtype(String blood) {
		this.bloodtype = blood;
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
	public void setPractitionerRole(String practitionerRole) {
		this.practitionerRole = practitionerRole;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the exams
	 */
	public Set<Exam> getExams() {
		return exams;
	}

	/**
	 * @param exams
	 *            the exams to set
	 */
	public void setExams(Set<Exam> exams) {
		this.exams = exams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [given=" + given + ", family=" + family + ", password=" + password + ", ndi=" + ndi + ", ndivalue="
				+ ndivalue + ", gender=" + gender + ", birthDate=" + birthDate + ", maritalStatus=" + maritalStatus
				+ ", telhome=" + telhome + ", telmobile=" + telmobile + ", telwork=" + telwork + ", email=" + email
				+ ", line=" + line + ", city=" + city + ", givenc=" + givenc + ", familyc=" + familyc + ", telc=" + telc
				+ ", relationship=" + relationship + ", managingOrganization=" + managingOrganization + ", bloodtype="
				+ bloodtype + ", id=" + id + ", practitionerRole=" + practitionerRole + ", roles=" + roles + ", exams="
				+ exams + ", labs=" + labs + "]";
	}

}
