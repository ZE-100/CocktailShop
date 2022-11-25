package com.z100.cocktailshop.util.validators;

import com.z100.cocktailshop.exceptions.*;

import java.util.Arrays;
import java.util.Objects;

public class ObjectValidator {

	@SafeVarargs
	public static <T> void notNull(T... t) {
		if (Arrays.stream(t).anyMatch(Objects::isNull))
			throw new ApiException();
	}

	@SafeVarargs
	public static <T> void notNull(String message, T... t) {
		if (Arrays.stream(t).anyMatch(Objects::isNull))
			throw new ApiException(message);
	}
}
