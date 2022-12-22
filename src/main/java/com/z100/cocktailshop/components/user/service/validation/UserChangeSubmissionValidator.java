package com.z100.cocktailshop.components.user.service.validation;

import com.z100.cocktailshop.components.user.dto.UserInDTO;
import com.z100.cocktailshop.components.user.service.validation.types.UserChangeValidationField;
import com.z100.cocktailshop.components.user.service.validation.types.UserChangeValidationReason;
import com.z100.cocktailshop.util.validators.ValidationResult;
import com.z100.cocktailshop.util.validators.Validator;
import com.z100.cocktailshop.util.validators.ValidatorBase;
import org.springframework.stereotype.Component;

@Component
public class UserChangeSubmissionValidator implements ValidatorBase<UserInDTO> {

	public ValidationResult validate(UserInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getId()).ifNegative()
				.reason(UserChangeValidationReason.NEW_ID_INVALID)
				.field(UserChangeValidationField.NEW_ID)
				.finish(validationResult);

		Validator.reject(user.getUsername()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_USERNAME_NULL)
				.field(UserChangeValidationField.NEW_USERNAME)
				.finish(validationResult);

		Validator.reject(user.getEmail()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_EMAIL_NULL)
				.field(UserChangeValidationField.NEW_EMAIL)
				.finish(validationResult);

		Validator.reject(user.getPassword()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_PASSWORD_NULL)
				.field(UserChangeValidationField.NEW_PASSWORD)
				.finish(validationResult);

		return validationResult;
	}
}
