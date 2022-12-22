package com.z100.cocktailshop.components.cocktail.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationField;

public enum CocktailValidationField implements ValidationField {

	NAME("name"),
	DESCRIPTION("description"),
	PICTURE("picture"),
	PRICE("price");

	private final String location;

	CocktailValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
