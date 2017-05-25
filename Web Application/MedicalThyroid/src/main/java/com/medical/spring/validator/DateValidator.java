package com.medical.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class DateValidator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String DATE_PATTERN = "(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}";

	public DateValidator() {
		pattern = Pattern.compile(DATE_PATTERN);
	}

	public boolean valid(final String date) {

		matcher = pattern.matcher(date);
		return matcher.matches();
	}
}