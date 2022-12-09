package com.z100.cocktailshop.components.user.service.validation;

import com.z100.cocktailshop.components.user.dto.UserChangeInDTO;
import com.z100.cocktailshop.components.user.service.validation.types.UserChangeValidationField;
import com.z100.cocktailshop.components.user.service.validation.types.UserChangeValidationReason;
import com.z100.cocktailshop.util.validators.ValidationResult;
import com.z100.cocktailshop.util.validators.Validator;
import com.z100.cocktailshop.util.validators.ValidatorBase;
import org.springframework.stereotype.Component;

@Component
public class UserChangeSubmissionValidator implements ValidatorBase<UserChangeInDTO> {

	public ValidationResult validate(UserChangeInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getNewEmail()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_EMAIL_NULL)
				.field(UserChangeValidationField.NEW_EMAIL)
				.finish(validationResult);

		Validator.reject(user.getNewPassword()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_PASSWORD_NULL)
				.field(UserChangeValidationField.NEW_PASSWORD)
				.finish(validationResult);

		return validationResult;
	}
}
