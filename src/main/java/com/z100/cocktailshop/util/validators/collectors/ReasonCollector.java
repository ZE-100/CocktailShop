package com.z100.cocktailshop.util.validators.collectors;

import com.z100.cocktailshop.util.validators.types.ValidationReason;

public interface ReasonCollector extends ResultCollector {

	FieldCollector reason(ValidationReason reason);
}
