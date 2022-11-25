package com.z100.cocktailshop.util.validators;

import com.z100.cocktailshop.util.validators.types.ValidationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationResultEntry {

	private ValidationStatus status;

	private String reason;

	private List<String> details;

	public ValidationResultEntry(ValidationStatus status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public ValidationResultEntry(ValidationStatus status, String reason, List<String> details) {
		this.status = status;
		this.reason = reason;
		this.details = details;
	}

	private boolean isInfo() {
		return this.status == ValidationStatus.INFO;
	}

	private boolean isWarning() {
		return this.status == ValidationStatus.WARNING;
	}

	private boolean isError() {
		return this.status == ValidationStatus.ERROR;
	}

	private boolean hasDetails() {
		return this.details.size() > 0;
	}
}

