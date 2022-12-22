package com.z100.cocktailshop.components.cocktail.service.validation.types;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public enum CocktailValidationReason implements ValidationReason {

	NAME_NULL("Cocktail must have a name"),
	DESCRIPTION_NULL("Cocktail must have a description"),
	PICTURE_NULL("Cocktail must have a picture"),
	PRICE_INVALID("The price is invalid"),
	PRICE_NULL("Cocktail must have a price");

	String reason;

	CocktailValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
