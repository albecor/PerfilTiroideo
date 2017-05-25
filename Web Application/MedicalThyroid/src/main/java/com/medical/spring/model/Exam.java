package com.medical.spring.model;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class Exam {

	private String code;
	private String displayCode;
	private String systemCode;
	private String valueQuantity;
	private String low;
	private String high;
	private String referenceSubject;
	private String displaySubject;
	private String unit;
	private String comments;
	private String issued;
	private String interpretation;
	private String entity;
	private int order;
	private int done;
	private String referencePerfomer;
	private String displayPerformer;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exam [code=" + code + ", displayCode=" + displayCode + ", systemCode=" + systemCode + ", valueQuantity="
				+ valueQuantity + ", low=" + low + ", high=" + high + ", referenceSubject=" + referenceSubject
				+ ", displaySubject=" + displaySubject + ", unit=" + unit + ", comments=" + comments + ", issued="
				+ issued + ", interpretation=" + interpretation + ", entity=" + entity + ", order=" + order + ", done="
				+ done + ", referencePerfomer=" + referencePerfomer + ", displayPerformer=" + displayPerformer + "]";
	}

	/**
	 * @return the valueQuantity
	 */
	public String getValueQuantity() {
		return valueQuantity;
	}

	/**
	 * @param valueQuantity
	 *            the valueQuantity to set
	 */
	public void setValueQuantity(String valueQuantity) {
		this.valueQuantity = valueQuantity;
	}

	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the referencePerfomer
	 */
	public String getReferencePerfomer() {
		return referencePerfomer;
	}

	/**
	 * @param referencePerfomer
	 *            the referencePerfomer to set
	 */
	public void setReferencePerfomer(String referencePerfomer) {
		this.referencePerfomer = referencePerfomer;
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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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

	/**
	 * @return the done
	 */
	public int getDone() {
		return done;
	}

	/**
	 * @param done
	 *            the done to set
	 */
	public void setDone(int done) {
		this.done = done;
	}

}
