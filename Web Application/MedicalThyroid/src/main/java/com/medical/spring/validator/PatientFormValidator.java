package com.medical.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.medical.spring.model.Patient;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class PatientFormValidator implements Validator {

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	DateValidator dateValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Patient.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Patient patient = (Patient) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "given", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "family", "family.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndivalue", "ndivalue.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telmobile", "telmobile.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telwork", "telwork.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telhome", "telhome.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "line", "address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managingOrganization", "managingOrganization.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "givenc", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familyc", "family.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telc", "telc.required");

		if (!dateValidator.valid(patient.getBirthDate())) {
			errors.rejectValue("birthDate", "birthDate.pattern");
		}

		if (!patient.getPassword().equals(patient.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "password.diff");
		}

		if (patient.getNdi().equalsIgnoreCase("none")) {
			errors.rejectValue("ndi", "ndi.required");
		}

		if (patient.getGender().equalsIgnoreCase("none")) {
			errors.rejectValue("gender", "gender.required");
		}

		if (!emailValidator.valid(patient.getEmail())) {
			errors.rejectValue("email", "email.pattern");
		}

		if (patient.getRelationship().equalsIgnoreCase("none")) {
			errors.rejectValue("relationship", "relationship.required");
		}

		if (patient.getMaritalStatus().equalsIgnoreCase("none")) {
			errors.rejectValue("maritalStatus", "maritalStatus.required");
		}

		if (patient.getBlood().equalsIgnoreCase("none")) {
			errors.rejectValue("blood", "blood.required");
		}
	}
}