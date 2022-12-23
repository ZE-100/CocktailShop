package com.z100.cocktailshop.util.validators.rejectors;

import com.z100.cocktailshop.util.validators.Pass;
import com.z100.cocktailshop.util.validators.collectors.ReasonCollector;

public class IntegerRejector extends BaseRejector<Integer> {

	public IntegerRejector(Pass pass, Integer entry) {
		super(pass, entry);
	}

	public ReasonCollector ifNegative() {

		if (entry > 0) {
			this.reject();
		} else {
			this.accept();
		}

		return this.pass;
	}

	public ReasonCollector ifPositive() {

		if (entry.toString().charAt(0) == '-') {
			this.reject();
		} else {
			this.accept();
		}

		return this.pass;
	}

}

