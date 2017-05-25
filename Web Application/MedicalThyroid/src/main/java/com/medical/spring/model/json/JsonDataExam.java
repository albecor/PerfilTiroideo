package com.medical.spring.model.json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.medical.spring.model.Exam;
import com.medical.spring.model.json.utilities.Code;
import com.medical.spring.model.json.utilities.Data;
import com.medical.spring.model.json.utilities.DataCode;
import com.medical.spring.model.json.utilities.DataCoding;
import com.medical.spring.model.json.utilities.DataInter;
import com.medical.spring.model.json.utilities.DataPerformer;
import com.medical.spring.model.json.utilities.DataRange;
import com.medical.spring.model.json.utilities.DataSubject;
import com.medical.spring.model.json.utilities.DataValue;
import com.medical.spring.model.json.utilities.Interpretation;
import com.medical.spring.model.json.utilities.Performer;
import com.medical.spring.model.json.utilities.Range;
import com.medical.spring.model.json.utilities.Subject;
import com.medical.spring.model.json.utilities.Value;
import com.medical.spring.model.utilities.DefaultThyroidProfile;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class JsonDataExam {

	private JsonElement code;
	private JsonElement subject;
	private JsonElement referenceRange;
	private JsonElement interpretation;
	private JsonElement performer;
	private JsonElement valueQuantity;
	private String comments;
	private String issued;
	private String entity;
	private int order;
	private int done;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonDataExam [code=" + code + ", subject=" + subject + ", referenceRange=" + referenceRange
				+ ", interpretation=" + interpretation + ", performer=" + performer + ", valueQuantity=" + valueQuantity
				+ ", comments=" + comments + ", issued=" + issued + ", entity=" + entity + ", order=" + order
				+ ", done=" + done + "]";
	}

	/**
	 * generate Json of the exam
	 * @param exam - object exam
	 */
	public JsonDataExam(Exam exam) {

		exam = new DefaultThyroidProfile().getExam(exam);

		Code CODE = new Code();
		DataCode dataCode = new DataCode();
		DataCoding dataCoding = new DataCoding();
		dataCoding.setCode(exam.getCode());
		dataCoding.setSystem(exam.getSystemCode());

		dataCoding.setDisplay(exam.getDisplayCode());

		dataCode.setDataCod(new DataCoding[] { dataCoding });
		CODE.setCode(dataCode);

		this.code = new Gson().toJsonTree(CODE);

		Subject SUBJECT = new Subject();
		DataSubject dataSub = new DataSubject();
		dataSub.setReference(exam.getReferenceSubject());
		dataSub.setDisplay(exam.getDisplaySubject());
		SUBJECT.setDataSubject(dataSub);
		this.subject = new Gson().toJsonTree(SUBJECT);

		Performer PERFORMER = new Performer();
		DataPerformer dataPerf = new DataPerformer();
		dataPerf.setReference(exam.getReferencePerfomer());
		dataPerf.setDisplay(exam.getDisplayPerformer());
		PERFORMER.setDataPerformer(new DataPerformer[] { dataPerf });
		this.performer = new Gson().toJsonTree(PERFORMER);

		this.order = exam.getOrder();
		this.entity = exam.getEntity();

		Range RANGE = new Range();
		DataRange dataRange = new DataRange();
		Data lData = new Data();
		lData.setValue(exam.getLow());
		lData.setUnit(exam.getUnit());
		lData.setSystem("http://unitsofmeasure.org");
		lData.setCode(exam.getUnit());
		Data hData = new Data();
		hData.setValue(exam.getHigh());
		hData.setUnit(exam.getUnit());
		hData.setSystem("http://unitsofmeasure.org");
		hData.setCode(exam.getUnit());
		dataRange.setDataLow(new Data[] { lData });
		dataRange.setDataHigh(new Data[] { hData });
		RANGE.setDataRange(new DataRange[] { dataRange });

		this.referenceRange = new Gson().toJsonTree(RANGE);

		if (exam.getDone() == 1) {
			this.issued = exam.getIssued();
			this.done = exam.getDone();

			// Interpretation
			Interpretation INTER = new Interpretation();
			DataInter dataInter = new DataInter();
			DataCoding dCoding = new DataCoding();
			String cCode;
			String dDisplay;
			int c1 = Double.compare(Double.parseDouble(exam.getValueQuantity()), Double.parseDouble(exam.getLow()));
			int c2 = Double.compare(Double.parseDouble(exam.getValueQuantity()), Double.parseDouble(exam.getHigh()));

			if (c1 > 0 && c2 < 0) {
				cCode = "N";
				dDisplay = "Normal";
			} else if (c1 < 0) {
				cCode = "L";
				dDisplay = "Low";

			} else if (c2 > 0) {
				cCode = "H";
				dDisplay = "High";
			} else {
				cCode = "ND";
				dDisplay = "Not Detected";
			}
			dCoding.setCode(cCode);
			dCoding.setSystem("http://hl7.org/fhir/v2/0078");
			dCoding.setDisplay(dDisplay);
			dataInter.setInterpretation(new DataCoding[] { dCoding });
			INTER.setDataInter(dataInter);
			this.interpretation = new Gson().toJsonTree(INTER);

			Value VALUE = new Value();
			DataValue dataValue = new DataValue();
			dataValue.setValue(exam.getValueQuantity());
			dataValue.setUnit(exam.getUnit());
			dataValue.setSystem("http://unitsofmeasure.org");
			dataValue.setCode(exam.getUnit());
			VALUE.setDataValue(dataValue);
			this.valueQuantity = new Gson().toJsonTree(VALUE);

			this.comments = exam.getComments();
		} else {
			this.valueQuantity = null;
			this.interpretation = null;
			this.comments = "";
			this.issued = null;
		}

	}

	/**
	 * @return the performer
	 */
	public JsonElement getPerformer() {
		return performer;
	}

	/**
	 * @param performer
	 *            the performer to set
	 */
	public void setPerformer(JsonElement performer) {
		this.performer = performer;
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
	 * @return the code
	 */
	public JsonElement getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(JsonElement code) {
		this.code = code;
	}

	/**
	 * @return the subject
	 */
	public JsonElement getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(JsonElement subject) {
		this.subject = subject;
	}

	/**
	 * @return the referenceRange
	 */
	public JsonElement getReferenceRange() {
		return referenceRange;
	}

	/**
	 * @param referenceRange
	 *            the referenceRange to set
	 */
	public void setReferenceRange(JsonElement referenceRange) {
		this.referenceRange = referenceRange;
	}

	/**
	 * @return the interpretation
	 */
	public JsonElement getInterpretation() {
		return interpretation;
	}

	/**
	 * @param interpretation
	 *            the interpretation to set
	 */
	public void setInterpretation(JsonElement interpretation) {
		this.interpretation = interpretation;
	}

	/**
	 * @return the value
	 */
	public JsonElement getValueQuantity() {
		return valueQuantity;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValueQuantity(JsonElement value) {
		this.valueQuantity = value;
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
