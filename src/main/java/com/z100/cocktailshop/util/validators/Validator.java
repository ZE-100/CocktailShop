package com.z100.cocktailshop.util.validators;

import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;
import com.z100.cocktailshop.util.validators.rejectors.*;
import org.springframework.stereotype.Component;

@Component
public class Validator {

	public static ReasonCollector reject() {
		return Pass.reject();
	}

	public static BaseRejector<Object> reject(Object entry) {
		return new BaseRejector<>(new Pass(), entry);
	}

	public static StringRejector reject(String entry) {
		return new StringRejector(new Pass(), entry);
	}
	
	public static BooleanRejector reject(Boolean entry) {
		return new BooleanRejector(new Pass(), entry);
	}

	public static IntegerRejector reject(Integer entry) {
		return new IntegerRejector(new Pass(), entry);
	}

	public static DoubleRejector reject(Double entry) {
		return new DoubleRejector(new Pass(), entry);
	}

	public static LongRejector reject(Long entry) {
		return new LongRejector(new Pass(), entry);
	}
}
