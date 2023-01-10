package com.z100.cocktailshop.util.validators;

import com.z100.cocktailshop.exceptions.*;

import java.util.Arrays;
import java.util.Objects;

public class ObjectValidator {

	@SafeVarargs
	public static <Obj> void notNull(Obj... obj) {
		if (Arrays.stream(obj).anyMatch(Objects::isNull))
			throw new ApiException("Object(s) cannot be null");
	}

	@SafeVarargs
	public static <Obj> void notNull(String message, Obj... obj) {
		if (Arrays.stream(obj).anyMatch(Objects::isNull))
			throw new ApiException(message);
	}
}
