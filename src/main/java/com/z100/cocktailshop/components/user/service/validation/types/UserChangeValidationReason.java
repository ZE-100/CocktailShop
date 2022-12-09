package com.z100.cocktailshop.components.user.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public enum UserChangeValidationReason implements ValidationReason {

	NEW_EMAIL_NULL("New e-mail cannot be null"),
	NEW_PASSWORD_NULL("New password cannot be null"),
	NEW_PASSWORD_INVALID("New password doesn't match regex");

	String reason;

	UserChangeValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
