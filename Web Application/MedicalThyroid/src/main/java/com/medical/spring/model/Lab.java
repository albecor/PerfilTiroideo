
package com.medical.spring.model;

/**
 * class user root
 * 
 * @author GTST : Grupo de Tratamiento de Señales y telecomunicaciones
 */
public class Lab {

	private int id;
	private String ndi;
	private String ndivalue;
	private String name;
	private String phone;
	private String password;
	private String email;
	private String address;
	private String confirmPassword;
	private String entitylab;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Lab [id=" + id + ", ndi=" + ndi + ", ndivalue=" + ndivalue + ", name=" + name + ", phone=" + phone
				+ ", password=" + password + ", email=" + email + ", address=" + address + ", confirmPassword="
				+ confirmPassword + ", entitylab=" + entitylab + "]";
	}

	/**
	 * @return the entitylab
	 */
	public String getEntitylab() {
		return entitylab;
	}

	/**
	 * @param entitylab
	 *            the entitylab to set
	 */
	public void setEntitylab(String entitylab) {
		this.entitylab = entitylab;
	}

}
