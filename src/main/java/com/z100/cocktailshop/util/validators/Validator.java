package com.z100.cocktailshop.util.validators;

import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;
import com.z100.cocktailshop.util.validators.rejectors.BaseRejector;
import com.z100.cocktailshop.util.validators.rejectors.BooleanRejector;
import com.z100.cocktailshop.util.validators.rejectors.IntegerRejector;
import com.z100.cocktailshop.util.validators.rejectors.StringRejector;
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

}
