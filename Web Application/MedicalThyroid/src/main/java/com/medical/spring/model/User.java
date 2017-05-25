package com.medical.spring.model;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class User {

	private String username;
	private String password;
	private boolean Admin;
	private boolean Patient;
	private boolean Personal;
	private boolean Lab;

	/**
	 * @return role count
	 */
	public int roleCount() {
		int i = 0;
		if (this.isAdmin())
			i++;
		if (this.isPatient())
			i++;
		if (this.isPersonal())
			i++;
		if (this.isLab())
			i++;
		return i;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", Admin=" + Admin + ", Patient=" + Patient
				+ ", Personal=" + Personal + ", Lab=" + Lab + "]";
	}

	/**
	 * @return the lab
	 */
	public boolean isLab() {
		return Lab;
	}

	/**
	 * @param lab
	 *            the lab to set
	 */
	public void setLab(boolean lab) {
		Lab = lab;
	}

	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return Admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(boolean admin) {
		Admin = admin;
	}

	/**
	 * @return the patient
	 */
	public boolean isPatient() {
		return Patient;
	}

	/**
	 * @param patient
	 *            the patient to set
	 */
	public void setPatient(boolean patient) {
		Patient = patient;
	}

	/**
	 * @return the personal
	 */
	public boolean isPersonal() {
		return Personal;
	}

	/**
	 * @param personal
	 *            the personal to set
	 */
	public void setPersonal(boolean personal) {
		Personal = personal;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

}
