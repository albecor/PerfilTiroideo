package com.medical.spring.json;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.medical.spring.model.json.utilities.Code;
import com.medical.spring.model.json.utilities.Data;
import com.medical.spring.model.json.utilities.DataCode;
import com.medical.spring.model.json.utilities.DataCoding;
import com.medical.spring.model.json.utilities.DataInter;
import com.medical.spring.model.json.utilities.DataRange;
import com.medical.spring.model.json.utilities.DataSubject;
import com.medical.spring.model.json.utilities.DataValue;
import com.medical.spring.model.json.utilities.Interpretation;
import com.medical.spring.model.json.utilities.Range;
import com.medical.spring.model.json.utilities.Subject;
import com.medical.spring.model.json.utilities.Value;

@Component
public class LDH {
	public static void main(String args[]) throws SQLException, IOException {

		Code CODE = new Code();
		DataCode dataCode = new DataCode();
		DataCoding dataCoding = new DataCoding();
		dataCoding.setDisplay("LDH Lactate dehydrogenase");
		dataCoding.setCode("2532-0");
		dataCoding.setSystem("https://s.details.loinc.org/LOINC/2532-0.html");

		dataCode.setDataCod(new DataCoding[] { dataCoding });
		CODE.setCode(dataCode);

		JsonElement code = new Gson().toJsonTree(CODE);
		System.out.println(code);

		String valor = "1";

		double vValue = Double.parseDouble(valor);

		Value VALUE = new Value();
		DataValue dataValue = new DataValue();
		dataValue.setValue(valor);
		dataValue.setUnit("U/L");
		dataValue.setSystem("http://unitsofmeasure.org");
		dataValue.setCode("U/L");
		VALUE.setDataValue(dataValue);
		JsonElement value = new Gson().toJsonTree(VALUE);
		System.out.println(value);

		// Reference Range
		double low = 30.0;
		double high = 120.0;
		Range RANGE = new Range();
		DataRange dataRange = new DataRange();
		Data lData = new Data();
		lData.setValue(Double.toString(low));
		lData.setUnit("U/L");
		lData.setSystem("http://unitsofmeasure.org");
		lData.setCode("U/L");
		Data hData = new Data();
		hData.setValue(Double.toString(high));
		hData.setUnit("U/L");
		hData.setSystem("http://unitsofmeasure.org");
		hData.setCode("U/L");
		dataRange.setDataLow(new Data[] { lData });
		dataRange.setDataHigh(new Data[] { hData });
		RANGE.setDataRange(new DataRange[] { dataRange });
		JsonElement referenceRange = new Gson().toJsonTree(RANGE);
		System.out.println(referenceRange);

		// Interpretation
		Interpretation INTER = new Interpretation();
		DataInter dataInter = new DataInter();
		DataCoding dCoding = new DataCoding();
		String cCode;
		String dDisplay;
		int c1 = Double.compare(vValue, low);
		int c2 = Double.compare(vValue, high);
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
		JsonElement interpretation = new Gson().toJsonTree(INTER);

		System.out.println(interpretation);

		Subject SUBJECT = new Subject();
		DataSubject dataSub = new DataSubject();
		dataSub.setReference("C.C");
		dataSub.setDisplay("nombre");
		SUBJECT.setDataSubject(dataSub);
		JsonElement subject = new Gson().toJsonTree(SUBJECT);
		System.out.println(subject);

	}

}