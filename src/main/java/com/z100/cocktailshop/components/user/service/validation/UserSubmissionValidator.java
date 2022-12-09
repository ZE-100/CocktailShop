package com.z100.cocktailshop.components.user.service.validation;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.service.validation.types.UserValidationField;
import com.z100.cocktailshop.components.user.service.validation.types.UserValidationReason;
import com.z100.cocktailshop.util.validators.ValidationResult;
import com.z100.cocktailshop.util.validators.Validator;
import com.z100.cocktailshop.util.validators.ValidatorBase;
import org.springframework.stereotype.Component;

@Component
public class UserSubmissionValidator implements ValidatorBase<UserInDTO> {

	public ValidationResult validate(UserInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getUsername()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.field(UserValidationField.USERNAME)
				.finish(validationResult);

		Validator.reject(user.getEmail()).ifEmpty()
				.reason(UserValidationReason.EMAIL_NULL)
				.field(UserValidationField.EMAIL)
				.finish(validationResult);

		Validator.reject(user.getPassword()).ifEmpty()
				.reason(UserValidationReason.PASSWORD_NULL)
				.field(UserValidationField.PASSWORD)
				.finish(validationResult);

		return validationResult;
	}
}
