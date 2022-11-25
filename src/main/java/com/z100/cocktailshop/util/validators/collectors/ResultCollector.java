package com.z100.cocktailshop.util.validators.collectors;

import com.z100.cocktailshop.util.validators.ValidationResult;

public interface ResultCollector {

	void finish(ValidationResult validationResult);
}
