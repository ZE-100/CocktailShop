package com.z100.cocktailshop.components.cocktail.service.validation;

import com.z100.cocktailshop.components.cocktail.dto.CocktailInDTO;
import com.z100.cocktailshop.components.cocktail.service.validation.types.CocktailChangeValidationField;
import com.z100.cocktailshop.components.cocktail.service.validation.types.CocktailChangeValidationReason;
import com.z100.cocktailshop.util.validators.ValidationResult;
import com.z100.cocktailshop.util.validators.Validator;
import com.z100.cocktailshop.util.validators.ValidatorBase;
import org.springframework.stereotype.Component;

@Component
public class CocktailChangeSubmissionValidator implements ValidatorBase<CocktailInDTO> {

	public ValidationResult validate(CocktailInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getName()).ifEmpty()
				.reason(CocktailChangeValidationReason.NEW_NAME_NULL)
				.field(CocktailChangeValidationField.NEW_NAME)
				.finish(validationResult);

		Validator.reject(user.getDescription()).ifEmpty()
				.reason(CocktailChangeValidationReason.NEW_DESCRIPTION_NULL)
				.field(CocktailChangeValidationField.NEW_DESCRIPTION)
				.finish(validationResult);

		Validator.reject(user.getPicture()).ifEmpty()
				.reason(CocktailChangeValidationReason.NEW_PICTURE_NULL)
				.field(CocktailChangeValidationField.NEW_PICTURE)
				.finish(validationResult);

		Validator.reject(user.getPrice()).ifNull()
				.reason(CocktailChangeValidationReason.NEW_PRICE_NULL)
				.field(CocktailChangeValidationField.NEW_PRICE)
				.finish(validationResult);

		Validator.reject(user.getPrice()).ifNegative()
				.reason(CocktailChangeValidationReason.NEW_PRICE_INVALID)
				.field(CocktailChangeValidationField.NEW_PRICE)
				.finish(validationResult);

		return validationResult;
	}
}
