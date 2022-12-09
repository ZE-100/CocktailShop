package com.z100.cocktailshop.components.user.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public enum UserValidationReason implements ValidationReason {

	USERNAME_NULL("Username cannot be null"),
	EMAIL_NULL("E-mail cannot be null"),
	PASSWORD_NULL("Password cannot be null"),
	PASSWORD_INVALID("Password doesn't match regex");

	String reason;

	UserValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
