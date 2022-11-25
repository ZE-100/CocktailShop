package com.z100.cocktailshop.util.validators;

public interface ValidatorBase<T> {

	ValidationResult validate(T t);
}
