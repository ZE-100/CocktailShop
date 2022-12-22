package com.z100.cocktailshop.components.user.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public enum UserChangeValidationReason implements ValidationReason {

	NEW_ID_INVALID("Id cannot be invalid"),
	NEW_USERNAME_NULL("Username cannot be null"),
	NEW_EMAIL_NULL("E-mail cannot be null"),
	NEW_PASSWORD_NULL("Password cannot be null"),
	NEW_PASSWORD_INVALID("Password doesn't match regex");

	String reason;

	UserChangeValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
