package com.kumiko.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kumiko.springboot.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String> {
	@Override
	public void initialize(Phone phone) {
	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext cxt) {
		if (input == null) {
			return false;
		}
		return input.matches("[0-9()-]*");
	}

}
