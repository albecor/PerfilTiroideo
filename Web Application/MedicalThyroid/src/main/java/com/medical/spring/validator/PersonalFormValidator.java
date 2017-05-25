package com.medical.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.medical.spring.model.Personal;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class PersonalFormValidator implements Validator {

	@Autowired
	EmailValidator emailValidator;

	@Autowired
	DateValidator dateValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Personal.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Personal personal = (Personal) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "given", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDate", "birthDate.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "family", "family.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "line", "address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndivalue", "ndivalue.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telmobile", "telmobile.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telwork", "telwork.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "city.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "managingOrganization", "managingOrganization.required");

		if (personal.getNdi().equalsIgnoreCase("none")) {
			errors.rejectValue("ndi", "ndi.required");
		}

		if (personal.getGender().equalsIgnoreCase("none")) {
			errors.rejectValue("gender", "gender.required");
		}

		if (personal.getRole().equalsIgnoreCase("none")) {
			errors.rejectValue("role", "role.required");
		}

		if (!emailValidator.valid(personal.getEmail())) {
			errors.rejectValue("email", "email.pattern");
		}

		if (!dateValidator.valid(personal.getBirthDate())) {
			errors.rejectValue("birthDate", "birthDate.pattern");
		}

		if (!personal.getPassword().equals(personal.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "password.diff");
		}
	}
}