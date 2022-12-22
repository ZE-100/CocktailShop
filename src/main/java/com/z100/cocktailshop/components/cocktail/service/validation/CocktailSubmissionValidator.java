package com.z100.cocktailshop.components.cocktail.service.validation;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.service.validation.types.CocktailValidationField;
import com.z100.cocktailshop.components.cocktail.service.validation.types.CocktailValidationReason;
import com.z100.cocktailshop.util.validators.ValidationResult;
import com.z100.cocktailshop.util.validators.Validator;
import com.z100.cocktailshop.util.validators.ValidatorBase;
import org.springframework.stereotype.Component;

@Component
public class CocktailSubmissionValidator implements ValidatorBase<CocktailInDTO> {

	public ValidationResult validate(CocktailInDTO cocktailIn) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(cocktailIn.getName()).ifEmpty()
				.reason(CocktailValidationReason.NAME_NULL)
				.field(CocktailValidationField.NAME)
				.finish(validationResult);

		Validator.reject(cocktailIn.getDescription()).ifEmpty()
				.reason(CocktailValidationReason.DESCRIPTION_NULL)
				.field(CocktailValidationField.DESCRIPTION)
				.finish(validationResult);

		Validator.reject(cocktailIn.getPicture()).ifEmpty()
				.reason(CocktailValidationReason.PICTURE_NULL)
				.field(CocktailValidationField.PICTURE)
				.finish(validationResult);

		Validator.reject(cocktailIn.getPrice()).ifNull()
				.reason(CocktailValidationReason.PRICE_NULL)
				.field(CocktailValidationField.PRICE)
				.finish(validationResult);

		Validator.reject(cocktailIn.getPrice()).ifNegative()
				.reason(CocktailValidationReason.PRICE_INVALID)
				.field(CocktailValidationField.PRICE)
				.finish(validationResult);

		return validationResult;
	}
}
