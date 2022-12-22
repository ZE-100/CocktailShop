package com.z100.cocktailshop.util;

import com.z100.cocktailshop.util.validators.ValidationResult;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PROTECTED;

/**
 * Processor to handle various submissions
 *
 * @param <T> Type of the input-object
 * @param <E> Type of the output-object
 */
public abstract class SubmissionProcessor<T, E> {

	protected T submission;

	@Getter(PROTECTED)
	@Setter(PROTECTED)
	private E entity;

	private boolean isOk = true;

	public SubmissionProcessor<T, E> process(T t) {

		if (validate(t).isOk()) {

			pre(t);

			if (getEntity() == null)
				setEntity(mapSubmissionToEntity(t));

			persist(getEntity());

			post(t);
		} else {
			isOk = false;
		}

		return this;
	}

	public boolean isOk() {
		return isOk;
	}

	protected abstract ValidationResult validate(T t);

	protected abstract void persist(E e);

	protected abstract void pre(T t);

	protected abstract E mapSubmissionToEntity(T t);

	protected abstract void post(T t);
}
