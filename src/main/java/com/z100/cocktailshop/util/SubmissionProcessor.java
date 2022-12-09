package com.z100.cocktailshop.util;

import com.z100.cocktailshop.util.validators.ValidationResult;

public abstract class SubmissionProcessor<T> {

	protected T submission;

	private boolean isOk = true;

	public SubmissionProcessor<T> process(T t) {

		if (validate(t).isOk()) {

			prePersistOperations(t);

			persist(t);

			postPersistOperations(t);
		} else {
			isOk = false;
		}

		return this;
	}

	public boolean isOk() {
		return isOk;
	}

	protected abstract ValidationResult validate(T t);

	protected abstract void persist(T t);

	protected abstract void postPersistOperations(T t);

	protected abstract void prePersistOperations(T t);
}
