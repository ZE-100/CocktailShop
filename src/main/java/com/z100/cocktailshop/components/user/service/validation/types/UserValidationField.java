package com.z100.cocktailshop.components.user.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationField;

public enum UserValidationField implements ValidationField {

	ID("id"),
	EMAIL("email"),
	USERNAME("username"),
	PASSWORD("password"),
	NAME("name"),
	ROLE("role");

	private final String location;

	UserValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
