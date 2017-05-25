package com.medical.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.medical.spring.model.Admin;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class AdminFormValidator implements Validator {

	@Autowired
	EmailValidator emailValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Admin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Admin user = (Admin) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ndivalue", "ndivalue.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "phone.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirmPassword.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");

		if (user.getNdi().equalsIgnoreCase("none")) {
			errors.rejectValue("ndi", "ndi.required");
		}

		if (!emailValidator.valid(user.getEmail())) {
			errors.rejectValue("email", "email.pattern");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "password.diff");
		}
	}

}