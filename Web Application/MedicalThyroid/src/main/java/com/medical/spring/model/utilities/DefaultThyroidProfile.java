package com.medical.spring.model.utilities;

import com.medical.spring.model.Exam;

public class DefaultThyroidProfile {

	public Exam getExam(Exam exam) {

		switch (exam.getCode()) {
		case "3024-7":
			exam.setDisplayCode("Thyroxine (T4) free [Mass/volume] in Serum or Plasma");
			exam.setCode("3024-7");
			exam.setSystemCode("https://r.details.loinc.org/LOINC/3024-7.html?sections=Comprehensive");
			exam.setLow("0.71");
			exam.setHigh("1.85");
			exam.setUnit("ng/dL");
			break;
		case "3016-3":
			exam.setDisplayCode("Thyrotropin [Units/volume] in Serum or Plasma TSH");
			exam.setCode("3016-3");
			exam.setSystemCode("https://en.wikipedia.org/wiki/Thyroid-stimulating_hormone");
			exam.setLow("0.3");
			exam.setHigh("3.0");
			exam.setUnit("uIU/mL");
			break;
		case "3026-2":
			exam.setDisplayCode("Thyroxine (T4) [Mass/volume] in Serum or Plasma");
			exam.setCode("3026-2");
			exam.setSystemCode("https://s.details.loinc.org/LOINC/3026-2.html?sections=Simple ");
			exam.setLow("5.4");
			exam.setHigh("11.5");
			exam.setUnit("ng/dL");
			break;
		case "3053-6":
			exam.setDisplayCode("Triiodothyronine (T3) [Mass/volume] in Serum or Plasma");
			exam.setCode("3053-6");
			exam.setSystemCode("https://s.details.loinc.org/LOINC/3053-6.html?sections=Simple");
			exam.setLow("0.8");
			exam.setHigh("2.0");
			exam.setUnit("ng/mL");
			break;
		case "3051-0":
			exam.setDisplayCode("Triiodothyronine (T3) Free [Mass/volume] in Serum or Plasma");
			exam.setCode("3051-0");
			exam.setSystemCode("https://s.details.loinc.org/LOINC/3051-0.html?sections=Simple");
			exam.setLow("2.3");
			exam.setHigh("4.4");
			exam.setUnit("pg/mL");
			break;
		case "3013-0":
			exam.setDisplayCode("Thyroglobulin [Mass/volume] in Serum or Plasma");
			exam.setCode("3013-0");
			exam.setSystemCode("https://r.details.loinc.org/LOINC/2324-2.html?sections=Comprehensive");
			exam.setLow("5.0");
			exam.setHigh("25.0");
			exam.setUnit("ug/L");
			break;
		}

		return exam;

	}

	public Exam getT4free() {
		Exam exam = new Exam();
		exam.setDisplayCode("Thyroxine (T4) free [Mass/volume] in Serum or Plasma");
		exam.setCode("3024-7");
		exam.setSystemCode("https://r.details.loinc.org/LOINC/3024-7.html?sections=Comprehensive");
		exam.setLow("0.71");
		exam.setHigh("1.85");
		exam.setUnit("ng/dL");
		return exam;
	}

	public Exam getThyrotropin() {
		Exam exam = new Exam();
		exam.setDisplayCode("Thyrotropin [Units/volume] in Serum or Plasma TSH");
		exam.setCode("3016-3");
		exam.setSystemCode("https://en.wikipedia.org/wiki/Thyroid-stimulating_hormone");
		exam.setLow("0.3");
		exam.setHigh("3.0");
		exam.setUnit("uIU/mL");
		return exam;
	}

	public Exam getT4() {
		Exam exam = new Exam();
		exam.setDisplayCode("Thyroxine (T4) [Mass/volume] in Serum or Plasma");
		exam.setCode("3026-2");
		exam.setSystemCode("https://s.details.loinc.org/LOINC/3026-2.html?sections=Simple ");
		exam.setLow("5.4");
		exam.setHigh("11.5");
		exam.setUnit("ng/dL");
		return exam;
	}

	public Exam getT3() {
		Exam exam = new Exam();
		exam.setDisplayCode("Triiodothyronine (T3) [Mass/volume] in Serum or Plasma");
		exam.setCode("3053-6");
		exam.setSystemCode("https://s.details.loinc.org/LOINC/3053-6.html?sections=Simple");
		exam.setLow("0.8");
		exam.setHigh("2.0");
		exam.setUnit("ng/mL");
		return exam;
	}

	public Exam getT3free() {
		Exam exam = new Exam();
		exam.setDisplayCode("Triiodothyronine (T3) Free [Mass/volume] in Serum or Plasma");
		exam.setCode("3051-0");
		exam.setSystemCode("https://s.details.loinc.org/LOINC/3051-0.html?sections=Simple");
		exam.setLow("2.3");
		exam.setHigh("4.4");
		exam.setUnit("pg/mL");
		return exam;
	}

	public Exam getThyroglobulin() {
		Exam exam = new Exam();
		exam.setDisplayCode("Thyroglobulin [Mass/volume] in Serum or Plasma");
		exam.setCode("3013-0");
		exam.setSystemCode("https://r.details.loinc.org/LOINC/2324-2.html?sections=Comprehensive");
		exam.setLow("5.0");
		exam.setHigh("25.0");
		exam.setUnit("ug/L");
		return exam;
	}

}
