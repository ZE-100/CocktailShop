package com.z100.cocktailshop.components.cocktail.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationField;

public enum CocktailChangeValidationField implements ValidationField {

	NEW_NAME("newName"),
	NEW_DESCRIPTION("newDescription"),
	NEW_PICTURE("newPicture"),
	NEW_PRICE("newPrice");

	private final String location;

	CocktailChangeValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
