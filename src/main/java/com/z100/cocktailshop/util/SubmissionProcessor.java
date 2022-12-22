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
	private E submissionAsEntity;

	public void process(T t) {

		if (validate(t).isOk()) {

			pre(t);

			if (getSubmissionAsEntity() == null)
				setSubmissionAsEntity(mapSubmissionToEntity(t));

			persist(getSubmissionAsEntity());

			post(t);
		}
	}

	protected abstract ValidationResult validate(T t);

	protected abstract void persist(E e);

	protected abstract void pre(T t);

	protected abstract E mapSubmissionToEntity(T t);

	protected abstract void post(T t);
}
