package com.z100.cocktailshop.util.validators.collectors;

import com.z100.cocktailshop.util.validators.types.ValidationField;

public interface FieldCollector extends ResultCollector {

	ReasonCollector field(ValidationField field);
}
