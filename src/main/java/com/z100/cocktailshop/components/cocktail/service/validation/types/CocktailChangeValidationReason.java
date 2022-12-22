package com.z100.cocktailshop.components.cocktail.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public enum CocktailChangeValidationReason implements ValidationReason {

	NEW_NAME_NULL("Cocktail must have a name"),
	NEW_DESCRIPTION_NULL("Cocktail must have a description"),
	NEW_PICTURE_NULL("Cocktail must have a picture"),
	NEW_PRICE_INVALID("The price is invalid"),
	NEW_PRICE_NULL("Cocktail must have a price");

	String reason;

	CocktailChangeValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
