package com.z100.cocktailshop.components.user.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationField;

public enum UserChangeValidationField implements ValidationField {

	NEW_ID("id"),
	NEW_EMAIL("email"),
	NEW_USERNAME("username"),
	NEW_PASSWORD("password"),
	NEW_NAME("name"),
	NEW_ROLE("role");

	private final String location;

	UserChangeValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
