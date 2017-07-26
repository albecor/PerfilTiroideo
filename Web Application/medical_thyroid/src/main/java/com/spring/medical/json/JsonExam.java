package com.spring.medical.json;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.spring.medical.model.Exam;

/**
 * 
 * create Json for the exam
 *
 */
public class JsonExam {
	private Integer order;
	private String code;
	private String subject;
	private String referenceRange;
	private String interpretation;
	private String valueQuantity;
	private String labComments;
	private String performerComments;
	private String issued;
	private String performer;
	private byte done;

	public JsonExam(Exam exam) {
		this.order = exam.getOrder();
		setCode(exam.getCode(), exam.getSystemCode(), exam.getDisplayCode());
		setSubject(exam.getDisplaySubject(), exam.getReferenceSubject());
		if (exam.getValue() != null) {
			setValueQuantity(exam.getUnit(), exam.getUnit(), exam.getValue());
		}
		if (exam.getUnit() != null) {
			setReferenceRange(exam.getUnit(), exam.getUnit(), exam.getLow(), exam.getHigh());
		}

		this.labComments = exam.getLabComments();
		this.performerComments = exam.getPerformerComments();
		this.issued = exam.getIssued();
		setPerformer(exam.getDisplayPerformer(), exam.getReferencePerformer());
		this.done = exam.getDone();

		if (exam.getValue() != null) {
			float value = Float.parseFloat(exam.getValue());
			float low = Float.parseFloat(exam.getLow());
			float high = Float.parseFloat(exam.getHigh());
			String code = null;
			if (value > high) {
				code = "H";
			} else if (value < low) {
				code = "L";
			} else {
				code = "N";
			}
			setInterpretation(code);
		}
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
	public void setCode(String code, String system, String display) {
		JsonNodeFactory factory = JsonNodeFactory.instance;

		ArrayNode arrayCode = factory.arrayNode();
		arrayCode.add(new ObjectNode(new JsonNodeFactory(false)).put("code", code).put("system", system).put("display",
				display));

		ObjectNode coding = factory.objectNode();
		coding.set("coding", arrayCode);

		ObjectNode code1 = factory.objectNode();
		code1.set("code", coding);
		this.code = code1.toString();
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String display, String reference) {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode subject1 = factory.objectNode().put("display", display).put("reference", reference);
		ObjectNode subject = factory.objectNode();
		subject.set("subject", subject1);
		this.subject = subject.toString();
	}

	/**
	 * @return the referenceRange
	 */
	public String getReferenceRange() {
		return referenceRange;
	}

	/**
	 * @param referenceRange
	 *            the referenceRange to set
	 */
	public void setReferenceRange(String code, String unit, String low, String high) {
		JsonNodeFactory factory = JsonNodeFactory.instance;

		ArrayNode arrayLow = factory.arrayNode();
		arrayLow.add(new ObjectNode(new JsonNodeFactory(false)).put("code", code).put("unit", unit).put("value", low)
				.put("system", "http://unitsofmeasure.org"));

		ArrayNode arrayHigh = factory.arrayNode();
		arrayHigh.add(new ObjectNode(new JsonNodeFactory(false)).put("code", code).put("unit", unit).put("value", high)
				.put("system", "http://unitsofmeasure.org"));

		ObjectNode coding = factory.objectNode();
		coding.set("low", arrayLow);
		coding.set("high", arrayHigh);

		ObjectNode referenceRange = factory.objectNode();
		referenceRange.set("referenceRange", new ArrayNode(new JsonNodeFactory(false)).add(coding));
		this.referenceRange = referenceRange.toString();
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
	public void setInterpretation(String code) {

		String display = "";

		switch (code) {
		case "N":
			display = "Normal";
			break;
		case "H":
			display = "High";
			break;
		case "L":
			display = "Low";
			break;
		}

		JsonNodeFactory factory = JsonNodeFactory.instance;

		ArrayNode arrayCode = factory.arrayNode();
		arrayCode.add(new ObjectNode(new JsonNodeFactory(false)).put("code", code)
				.put("system", "http://hl7.org/fhir/v2/0078").put("display", display));

		ObjectNode coding = factory.objectNode();
		coding.set("coding", arrayCode);

		ObjectNode interpretation = factory.objectNode();
		interpretation.set("interpretation", coding);

		this.interpretation = interpretation.toString();
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
	public void setValueQuantity(String code, String unit, String value) {

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode quantity = factory.objectNode().put("code", code).put("unit", unit).put("value", value).put("system",
				"http://unitsofmeasure.org");
		ObjectNode valueQuantity = factory.objectNode();
		valueQuantity.set("valueQuantity", quantity);

		this.valueQuantity = valueQuantity.toString();
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
	 * @return the performer
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * @param performer
	 *            the performer to set
	 */
	public void setPerformer(String display, String reference) {

		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode performer1 = factory.objectNode().put("display", display).put("reference", reference);
		ObjectNode perfomer = factory.objectNode();
		perfomer.set("performer", performer1);

		this.performer = perfomer.toString();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonExam [order=" + order + ", code=" + code + ", subject=" + subject + ", referenceRange="
				+ referenceRange + ", interpretation=" + interpretation + ", valueQuantity=" + valueQuantity
				+ ", labComments=" + labComments + ", performerComments=" + performerComments + ", issued=" + issued
				+ ", performer=" + performer + ", done=" + done + "]";
	}

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

}
