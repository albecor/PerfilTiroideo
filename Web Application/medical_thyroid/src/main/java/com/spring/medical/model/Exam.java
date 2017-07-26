package com.spring.medical.model;

/**
 * 
 * class exam
 *
 */
public class Exam {

	private Integer order;
	private String code;
	private String systemCode;
	private String displayCode;
	private String displaySubject;
	private String referenceSubject;
	private String low;
	private String high;
	private String value;
	private String unit;
	private String labComments;
	private String performerComments;
	private String issued;
	private String referencePerformer;
	private String displayPerformer;
	private byte done;
	private String interpretation;

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the systemCode
	 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * @param systemCode
	 *            the systemCode to set
	 */
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
	 * @return the displayCode
	 */
	public String getDisplayCode() {
		return displayCode;
	}

	/**
	 * @param displayCode
	 *            the displayCode to set
	 */
	public void setDisplayCode(String displayCode) {
		this.displayCode = displayCode;
	}

	/**
	 * @return the displaySubject
	 */
	public String getDisplaySubject() {
		return displaySubject;
	}

	/**
	 * @param displaySubject
	 *            the displaySubject to set
	 */
	public void setDisplaySubject(String displaySubject) {
		this.displaySubject = displaySubject;
	}

	/**
	 * @return the referenceSubject
	 */
	public String getReferenceSubject() {
		return referenceSubject;
	}

	/**
	 * @param referenceSubject
	 *            the referenceSubject to set
	 */
	public void setReferenceSubject(String referenceSubject) {
		this.referenceSubject = referenceSubject;
	}

	/**
	 * @return the low
	 */
	public String getLow() {
		return low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(String low) {
		this.low = low;
	}

	/**
	 * @return the high
	 */
	public String getHigh() {
		return high;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(String high) {
		this.high = high;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the labComments
	 */
	public String getLabComments() {
		return labComments;
	}

	/**
	 * @param labComments
	 *            the labComments to set
	 */
	public void setLabComments(String labComments) {
		this.labComments = labComments;
	}

	/**
	 * @return the performerComments
	 */
	public String getPerformerComments() {
		return performerComments;
	}

	/**
	 * @param performerComments
	 *            the performerComments to set
	 */
	public void setPerformerComments(String performerComments) {
		this.performerComments = performerComments;
	}

	/**
	 * @return the issued
	 */
	public String getIssued() {
		return issued;
	}

	/**
	 * @param issued
	 *            the issued to set
	 */
	public void setIssued(String issued) {
		this.issued = issued;
	}

	/**
	 * @return the referencePerformer
	 */
	public String getReferencePerformer() {
		return referencePerformer;
	}

	/**
	 * @param referencePerformer
	 *            the referencePerformer to set
	 */
	public void setReferencePerformer(String referencePerformer) {
		this.referencePerformer = referencePerformer;
	}

	/**
	 * @return the displayPerformer
	 */
	public String getDisplayPerformer() {
		return displayPerformer;
	}

	/**
	 * @param displayPerformer
	 *            the displayPerformer to set
	 */
	public void setDisplayPerformer(String displayPerformer) {
		this.displayPerformer = displayPerformer;
	}

	/**
	 * @return the done
	 */
	public byte getDone() {
		return done;
	}

	/**
	 * @param done
	 *            the done to set
	 */
	public void setDone(byte done) {
		this.done = done;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the interpretation
	 */
	public String getInterpretation() {
		return interpretation;
	}

	/**
	 * @param interpretation
	 *            the interpretation to set
	 */
	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exam [order=" + order + ", code=" + code + ", systemCode=" + systemCode + ", displayCode=" + displayCode
				+ ", displaySubject=" + displaySubject + ", referenceSubject=" + referenceSubject + ", low=" + low
				+ ", high=" + high + ", value=" + value + ", unit=" + unit + ", labComments=" + labComments
				+ ", performerComments=" + performerComments + ", issued=" + issued + ", referencePerformer="
				+ referencePerformer + ", displayPerformer=" + displayPerformer + ", done=" + done + ", interpretation="
				+ interpretation + "]";
	}

}
